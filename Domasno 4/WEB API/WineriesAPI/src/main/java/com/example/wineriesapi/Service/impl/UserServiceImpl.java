package com.example.wineriesapi.Service.impl;

import com.example.wineriesapi.Model.Users;
import com.example.wineriesapi.Repository.UserRepository;
import com.example.wineriesapi.Service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkCredentials(String email, String password) {
        Users user = userRepository.findAll().stream().filter(users -> users.getEmail().equals(email) && users.getPassword().equals(password)).findFirst().orElse(null);
        return user != null;
    }

    @Override
    public boolean userExists(String email, String username) {
        Users user = userRepository.findAll().stream().filter(users -> users.getEmail().equals(email) || users.getUsername().equals(username)).findFirst().orElse(null);

        return user != null;
    }

    @Override
    public Users create(Users user) {
        Users newUser  = new Users(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword());

        return userRepository.save(newUser);
    }
}
