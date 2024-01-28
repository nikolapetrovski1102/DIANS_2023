package com.example.wineries.services.impl;

import com.example.wineries.models.User;
import com.example.wineries.services.UserService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_PATH = "src/main/resources/users.csv";

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);
                User user = createUserFromDataArray(data);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private User createUserFromDataArray(String[] data) {
        User user = new User();
        user.setId(Long.parseLong(data[0]));
        user.setUsername(data[1]);
        user.setName(data[2]);
        user.setSurname(data[3]);
        user.setEmail(data[4]);
        user.setPassword(data[5]);
        return user;
    }

    @Override
    public void addUser(User newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE_PATH, true))) {
            String userLine = userToCsvString(newUser);
            writer.write(userLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String userToCsvString(User user) {
        return String.format("%d,%s,%s,%s,%s,%s",
                user.getId(), user.getUsername(), user.getName(),
                user.getSurname(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getUserByEmail(String email) {
        return getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean checkUser(String email, String username) {
        return getAllUsers().stream().anyMatch(user -> user.getEmail().equals(email) || user.getUsername().equals(username));
    }

    @Override
    public boolean loginUser(String email, String password) {
        return getAllUsers().stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    @Override
    public boolean getUserById(Long id) {
        return getAllUsers().stream().anyMatch(user -> user.getId().equals(id));
    }
}