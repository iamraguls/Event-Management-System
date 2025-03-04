package com.project.EventManagementSystem.service;

import com.project.EventManagementSystem.dto.RegistrationDTO;
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
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<RegistrationDTO> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations.stream().map(RegistrationDTO::new).collect(Collectors.toList());  // 👈🔥 Convert to DTO
    }

    public String registerForEvent(Long userId, Long eventId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getCapacity() <= 0) {
            return "Event is fully booked!";
        }

        Optional<Registration> existingRegistration = registrationRepository.findByUserAndEvent(user,event);
        if(existingRegistration.isPresent()){
            return "User already registered for this event!";
        }

        Registration registration = new Registration();
        registration.setEvents(event);
        registration.setUsers(user);
        registration.setRegisteredAt(LocalDateTime.now());
        registrationRepository.save(registration);

        event.setCapacity(event.getCapacity() - 1);
        eventRepository.save(event);
        return "Registered successfully!";
    }


    public String cancelRegistrationForEvent(Long userId, Long eventId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        Optional<Registration> existingRegistration = registrationRepository.findByUserAndEvent(user,event);
        if(existingRegistration.isEmpty()){
            return "User not registered for this event!";
        }

        registrationRepository.delete(existingRegistration.get());

        event.setCapacity(event.getCapacity()+1);
        eventRepository.save(event);
        return "Registration cancelled successfully!";
    }

    public List<Registration> getUserRegistrations(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return registrationRepository.findByUser(user);
    }
}


