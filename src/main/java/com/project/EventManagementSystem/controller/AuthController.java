package com.project.EventManagementSystem.controller;

import com.project.EventManagementSystem.dto.UserRegistrationDTO;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    public String registerUser(UserRegistrationDTO userRegistrationDTO){
        return authService.registerUser(userRegistrationDTO);
    }


}
