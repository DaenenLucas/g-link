package Beesten.g_link.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        return ResponseEntity.ok(userService.registerUser(user));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
//        return ResponseEntity.ok(userService.loginUser(loginRequest));
//    }
}
