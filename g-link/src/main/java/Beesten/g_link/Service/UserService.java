package Beesten.g_link.Service;

import Beesten.g_link.Domain.UserProfile;
import Beesten.g_link.Repository.UserPersonalInformationRepository;
import Beesten.g_link.Repository.UserRepository;
import Beesten.g_link.Requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {

    }

    //momenteel eruit gehaald voor als we vaste db hebben dat de userRepository fatsoenlijk
    //werkt met de findEmail
    public UserProfile registerUser(String Name, String Email, String Password) {
        //if (userRepository.findByEmail(email).isPresent()) {
        //  throw new Exception("Email already in use");
        //}
        UserProfile userProfile = new UserProfile(Name, Email, Password);
        return userRepository.save(userProfile);
    }

    public void saveUser(UserRequest userRequest) {
        UserProfile userProfile = new UserProfile();
//        user.setId(Long.valueOf(userRequest.userId())); //id aangepast
        userProfile.setName(userRequest.name());
        userProfile.setEmail(userRequest.email());
        userProfile.setPassword(getBCryptPassword(userRequest.password()));
        System.out.println(userProfile.getName());
        userRepository.save(userProfile);
    }

    private String getBCryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public List<UserProfile> findMatchingProfiles(String email) {
        UserProfile user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found"));

        return userRepository.findAll().stream()
                .filter(profile -> !profile.getEmail().equals(email)) // Exclude the current user
                .filter(profile -> hasMatchingInterestsOrSkills(user, profile)) // Match criteria
                .collect(Collectors.toList());
    }

    private boolean hasMatchingInterestsOrSkills(UserProfile user, UserProfile profile) {
        // Basic matching logic: Check if interests or skills overlap
        return user.getInterests() != null && profile.getInterests() != null
                && user.getInterests().equalsIgnoreCase(profile.getInterests())
                || user.getSkills() != null && profile.getSkills() != null
                && user.getSkills().equalsIgnoreCase(profile.getSkills());
    }

}
