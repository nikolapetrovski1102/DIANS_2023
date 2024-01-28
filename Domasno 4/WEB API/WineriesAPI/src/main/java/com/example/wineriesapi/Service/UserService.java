package com.example.wineriesapi.Service;

import com.example.wineriesapi.Model.Users;

public interface UserService {

    boolean checkCredentials(String email, String password);

    boolean userExists(String email, String username);

    Users create(Users user);
}
