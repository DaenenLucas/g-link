package Beesten.g_link.Service;

import Beesten.g_link.Repository.UserRepository;
import Beesten.g_link.Requests.UserRequest;
import Beesten.g_link.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {

    }

    //momenteel eruit gehaald voor als we vaste db hebben dat de userRepository fatsoenlijk
    //werkt met de findEmail
    public User registerUser(String Name, String Email, String Password) {
        //if (userRepository.findByEmail(email).isPresent()) {
        //  throw new Exception("Email already in use");
        //}
        User user = new User(Name, Email, Password);
        return userRepository.save(user);
    }

    public void saveUser(UserRequest userRequest) {
        User user = new User();
//        user.setId(Long.valueOf(userRequest.userId())); //id aangepast
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(getBCryptPassword(userRequest.password()));
        System.out.println(user.getName());
        userRepository.save(user);
    }

    private String getBCryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
