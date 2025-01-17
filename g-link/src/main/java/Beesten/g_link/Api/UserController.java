package Beesten.g_link.Api;

import Beesten.g_link.Domain.UserProfile;
import Beesten.g_link.Repository.UserPersonalInformationRepository;
import Beesten.g_link.Repository.UserRepository;
import Beesten.g_link.Requests.UserRequest;
import Beesten.g_link.Service.UserService;
import Beesten.g_link.Domain.UserPersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userservice;
    private UserProfile userProfile;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPersonalInformationRepository userPersonalInformationRepository;

    @PostMapping
    public ResponseEntity saveuser(@RequestBody UserRequest userRequest){
        if (userRepository.findByEmail(userRequest.email())!=null)
        {
            return ResponseEntity.badRequest().body("Dit email is al in gebruik");
        }
        userservice.saveUser(userRequest);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Object> addPersonalInformation(@RequestBody UserPersonalInformation userPersonalInformation) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserProfile userProfile = userRepository.findByEmail(email);

        if (userProfile == null) {
            return ResponseEntity.badRequest().body("Gebruiker met dit e-mailadres bestaat niet.");
        }
        if (userProfile.getInterests() == null || userPersonalInformation.getJob() == null || userPersonalInformation.getDate_of_birth() == null) {
            return ResponseEntity.badRequest().body("Alle velden (gewicht, lengte, geboortedatum) moeten ingevuld zijn.");
        }
        userPersonalInformation.setUser(userProfile);
        userPersonalInformationRepository.save(userPersonalInformation);

        return ResponseEntity.ok().body("De persoons informatie is toegevoegd / aangepast");
    }
}
