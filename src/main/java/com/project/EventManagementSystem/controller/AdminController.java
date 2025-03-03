package com.project.EventManagementSystem.controller;


import com.project.EventManagementSystem.dto.UserRegistrationDTO;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthService authService;

    public String registerAdmin(@RequestBody UserRegistrationDTO userRegistrationDTO, HttpServletRequest request){
        return authService.registerAdmin(userRegistrationDTO);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return authService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return authService.deleteUser(id);
    }

    @PutMapping("/users/{id}/role")
    public String updateUserRole(@PathVariable Long id, @RequestParam String role) {
        return authService.updateUserRole(id, role);
    }

}

