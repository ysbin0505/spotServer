package com.example.spotserver.service;

import com.example.spotserver.domain.User;
import com.example.spotserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public User updateUser(Long id, User userInfo) {
        User user = userRepository.findById(id).get(); // Optional 수정 필요
        String name = userInfo.getName();
        int age = userInfo.getAge();

        user.setName(name);
        user.setAge(age);
        return user;
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }



}
