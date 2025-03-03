package com.project.EventManagementSystem.service;

import com.project.EventManagementSystem.model.Event;
import com.project.EventManagementSystem.model.Registration;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.repository.EventRepository;
import com.project.EventManagementSystem.repository.RegistrationRepository;
import com.project.EventManagementSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public String registerForEvent(Long userId, Long eventId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        Optional<Registration> existingRegistration = registrationRepository.findByUsersAndEvent(user,event);
        if(existingRegistration.isPresent()){
            return "User already registered for this event!";
        }

        Registration registration = new Registration();
        registration.setEvents(event);
        registration.setUsers(user);
        registration.setRegisteredAt(LocalDateTime.now());
        registrationRepository.save(registration);
        return "Registered successfully!";
    }


    public String cancelRegistrationForEvent(Long userId, Long eventId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        Optional<Registration> existingRegistration = registrationRepository.findByUsersAndEvent(user,event);
        if(existingRegistration.isEmpty()){
            return "User not registered for this event!";
        }

        registrationRepository.delete(existingRegistration.get());
        return "Registration cancelled successfully!";
    }
}


