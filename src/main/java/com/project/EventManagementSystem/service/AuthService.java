package com.project.EventManagementSystem.service;

import com.project.EventManagementSystem.dto.AuthResponseDTO;
import com.project.EventManagementSystem.dto.LoginDTO;
import com.project.EventManagementSystem.dto.UserRegistrationDTO;
import com.project.EventManagementSystem.jwt.JwtService;
import com.project.EventManagementSystem.model.Users;
import com.project.EventManagementSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
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

    @Autowired
    private AuthenticationManager authenticationManager;

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

    public AuthResponseDTO loginUser(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        Users user = usersRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new RuntimeException("user not found"));
        AuthResponseDTO auth = new AuthResponseDTO();
        auth.setToken(jwtService.generateToken(new HashMap<>(), user, user.getRoles()));
        return auth;
    }

    public String registerAdmin(UserRegistrationDTO userRegistrationDTO) {
        boolean oldUser = usersRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent();
        if(oldUser){
            return "Email Already Exits!!";
        }
        Users user = new Users();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setPhone(userRegistrationDTO.getPhone());
        user.setRoles(List.of("USER","ADMIN"));
        usersRepository.save(user);
        return "Admin registered successfully!";
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public String deleteUser(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found!"));
        usersRepository.delete(user);
        return "User deleted successfully!";
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found!"));
    }

    public Users updateUserRole(Long id, String role) {
        Users user = usersRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found!"));
        List<String> updatedRoles = new ArrayList<>(user.getRoles()); // Convert to mutable list
        if (!updatedRoles.contains(role)) {
            updatedRoles.add(role);
        }
        user.setRoles(updatedRoles);
        return usersRepository.save(user);
    }

}
