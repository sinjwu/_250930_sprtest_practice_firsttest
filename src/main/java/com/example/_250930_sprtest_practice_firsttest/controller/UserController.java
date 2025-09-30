package com.example._250930_sprtest_practice_firsttest.controller;

import com.example._250930_sprtest_practice_firsttest.entity.User;
import com.example._250930_sprtest_practice_firsttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam String email) {
        User user = userService.createUser(name, email);
        return ResponseEntity.ok(user);
    }
}