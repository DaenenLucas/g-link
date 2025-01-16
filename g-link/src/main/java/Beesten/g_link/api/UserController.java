package Beesten.g_link.api;

import Beesten.g_link.Repository.UserPersonalInformationRepository;
import Beesten.g_link.Repository.UserRepository;
import Beesten.g_link.Requests.UserRequest;
import Beesten.g_link.Service.UserService;
import Beesten.g_link.domain.User;
import Beesten.g_link.domain.UserPersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userservice;

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
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Gebruiker met dit e-mailadres bestaat niet.");
        }
        if (userPersonalInformation.getWeight_kg() == null || userPersonalInformation.getHeight_cm() == null || userPersonalInformation.getDate_of_birth() == null) {
            return ResponseEntity.badRequest().body("Alle velden (gewicht, lengte, geboortedatum) moeten ingevuld zijn.");
        }
        userPersonalInformation.setUser(user);
        userPersonalInformationRepository.save(userPersonalInformation);

        return ResponseEntity.ok().body("De persoons informatie is toegevoegd / aangepast");
    }
}
