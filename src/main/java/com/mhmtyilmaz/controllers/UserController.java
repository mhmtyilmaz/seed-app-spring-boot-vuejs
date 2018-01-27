package com.mhmtyilmaz.controllers;

import com.mhmtyilmaz.entities.Role;
import com.mhmtyilmaz.entities.User;
import com.mhmtyilmaz.pojos.UserRegistration;
import com.mhmtyilmaz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration){
        if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfiguration()))
            return "Password do not match!";
        else if (userService.getUser(userRegistration.getUsername()) != null)
            return "User already exist!";

        User newUser = new User(userRegistration.getUsername(),
               userRegistration.getPassword(),
                Arrays.asList(new Role("USER")));

        userService.save(newUser);

        return "User Created!";
    }

    @GetMapping(value = "/users")
    public List<User> users(){
        return userService.getUsers();
    }
}
