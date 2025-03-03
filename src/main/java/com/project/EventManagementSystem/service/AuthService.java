package com.project.EventManagementSystem.service;

import com.project.EventManagementSystem.dto.LoginDTO;
import com.project.EventManagementSystem.dto.UserRegistrationDTO;
import com.project.EventManagementSystem.jwt.JwtService;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    public String registerUser(UserRegistrationDTO userRegistrationDTO) {
        boolean oldUser = usersRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent();
        if(oldUser){
            return "Email Already Exits!!";
        }
        Users user = new Users();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhone(userRegistrationDTO.getPhone());
        user.setRoles(List.of("USER"));
        usersRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(LoginDTO loginDTO) {

        Users user = usersRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new RuntimeException("user not found"));
        if (!bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return "Invalid email or password!";
        }
        String token = jwtService.generateToken(new HashMap<>(), user, user.getRoles());
    }
}
