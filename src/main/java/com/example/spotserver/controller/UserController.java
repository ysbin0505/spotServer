package com.example.spotserver.controller;

import com.example.spotserver.domain.User;
import com.example.spotserver.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public Map test() {
        System.out.println("누가 요청했어");
        Map<String, Object> m = new HashMap<>();
        m.put("name", "LSM");
        m.put("age", 10);
        return m;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        System.out.println("UserController.getUsers");
        List<User> allUsers = userService.findAllUsers();

        HttpStatus status = HttpStatus.OK;
        ResponseEntity response = new ResponseEntity(allUsers, status);

        //다른 방법 ResponseEntity<List<User>> response = ResponseEntity.ok(allUsers);
        return response;
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        System.out.println("UserController.addUser");
        return "ok";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        System.out.println("UserController.deleteUser");
        return "ok";
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(name="id") Long id, @RequestBody User userInfo) {

        System.out.println("UserController.updateUser");
        return userService.updateUser(id, userInfo);
    }
}
