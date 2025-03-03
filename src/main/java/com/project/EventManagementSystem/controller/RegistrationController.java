package com.project.EventManagementSystem.controller;

import com.project.EventManagementSystem.model.Registration;
import com.project.EventManagementSystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Registration> getAllRegistrations(){
        return registrationService.getAllRegistrations();
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerForEvent(@RequestParam Long userId, @RequestParam Long eventId){
        return registrationService.registerForEvent(userId, eventId);
    }

    @DeleteMapping("/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelRegistrationForEvent(@RequestParam Long userId, @RequestParam Long eventId){
        return registrationService.cancelRegistrationForEvent(userId, eventId);
    }

}
