package com.project.EventManagementSystem.repository;

import com.project.EventManagementSystem.model.Event;
import com.project.EventManagementSystem.model.Registration;
import com.project.EventManagementSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByUserAndEvent(Users user, Event event);
    List<Registration> findByUser(Users user);

}
