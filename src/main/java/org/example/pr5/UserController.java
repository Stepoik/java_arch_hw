package org.example.pr5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "Success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
        return "Success";
    }
}
