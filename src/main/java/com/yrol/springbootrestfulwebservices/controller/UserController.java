package com.yrol.springbootrestfulwebservices.controller;

import com.yrol.springbootrestfulwebservices.entity.User;
import com.yrol.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {


    // Doesn't need to user @Autowired annotation for DI since this class consist of only one parameter and SpringBoot will automatically inject it.
    private UserService userService;


    /**
     * User creation POST REST API
     **/
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
