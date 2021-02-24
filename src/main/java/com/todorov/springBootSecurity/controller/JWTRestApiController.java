package com.todorov.springBootSecurity.controller;

import com.todorov.springBootSecurity.db.UserRepository;
import com.todorov.springBootSecurity.model.UserEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class JWTRestApiController {
    private final UserRepository userRepository;

    public JWTRestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String test(){
        return "API test";
    }

    @GetMapping("/management/reports")
    public String reports(){
        return "Some management reports!";
    }

    @GetMapping("/admin/users")
    public List<UserEntity> users(){
        return this.userRepository.findAll();
    }
}
