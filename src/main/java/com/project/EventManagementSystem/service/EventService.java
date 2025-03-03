package com.project.EventManagementSystem.service;

import com.project.EventManagementSystem.dto.EventDTO;
import com.project.EventManagementSystem.model.Event;
import com.project.EventManagementSystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setCapacity(eventDTO.getCapacity());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());
        eventRepository.save(event);
        return event;
    }

    public Event updateEventById(EventDTO eventDTO, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setCapacity(eventDTO.getCapacity());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());
        eventRepository.save(event);
        return event;
    }

    public String deleteEventById(Long id) {
        eventRepository.deleteById(id);
        return "Event deleted successfully!";
    }

}
