package com.project.EventManagementSystem.controller;

import com.project.EventManagementSystem.dto.AuthResponseDTO;
import com.project.EventManagementSystem.dto.LoginDTO;
import com.project.EventManagementSystem.dto.UserRegistrationDTO;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/user/register")
    public String registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return authService.registerUser(userRegistrationDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO loginUser(@RequestBody LoginDTO loginDTO){
        return authService.loginUser(loginDTO);
    }

}
