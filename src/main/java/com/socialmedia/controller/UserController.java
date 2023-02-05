package com.socialmedia.controller;

import com.socialmedia.entity.User;
import com.socialmedia.service.UserService;
import com.socialmedia.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    @CrossOrigin
    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse createUser(@RequestBody User user) {
        userService.createUser(user);
        return new GenericResponse("User created!");
    }*/

    @PostMapping("users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new GenericResponse("User created!");
    }
}
