package com.practice.deploy.controller;

import com.practice.deploy.dto.UserDto;
import com.practice.deploy.entity.User;
import com.practice.deploy.repository.UserRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userInfo(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No User .."));
        return ResponseEntity.ok().body(user);
    }
}
