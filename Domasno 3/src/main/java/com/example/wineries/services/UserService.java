package com.example.wineries.services;

import com.example.wineries.models.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserByEmail(String Email);
    boolean checkUser(String Email, String Username);
    boolean loginUser(String Email, String Password);
    void addUser(User newUser);
    boolean getUserById(Long Id);

}
