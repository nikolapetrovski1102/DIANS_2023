package com.example.wineries.services.impl;

import com.example.wineries.models.User;
import com.example.wineries.services.UserService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAllUsers(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"));
            String line;
            List<User> userList = new ArrayList<>();
            String header = br.readLine();
            while ((line = br.readLine()) != null){
                String [] data = line.split(",",-1);
                User user = new User();
                user.setId(Long.parseLong(data[0]));
                user.setUsername(data[1]);
                user.setName(data[2]);
                user.setSurname(data[3]);
                user.setEmail(data[4]);
                user.setPassword(data[5]);
                userList.add(user);
            }

            return userList;

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/users.csv", true))) {
            StringBuilder userLine = new StringBuilder();
            userLine.append(newUser.getId()).append(",");
            userLine.append(newUser.getUsername()).append(",");
            userLine.append(newUser.getName()).append(",");
            userLine.append(newUser.getSurname()).append(",");
            userLine.append(newUser.getEmail()).append(",");
            userLine.append(newUser.getPassword()).append("\n");

            writer.write(userLine.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String Email) {
        try {
            return getAllUsers()
                    .stream()
                    .filter(user -> user.getEmail().equals(Email))
                    .findFirst()
                    .orElse(null);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean checkUser(String Email, String Username) {

        List<User> userList = getAllUsers();

        boolean userExistsByEmail = getAllUsers().stream().anyMatch(user -> user.getEmail().equals(Email));
        boolean userExistsByUsername = getAllUsers().stream().anyMatch(user -> user.getUsername().equals(Username));

        if (userExistsByEmail){
            return true;
        }
        else if (userExistsByUsername){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean loginUser(String Email, String Password) {

        boolean userExists = getAllUsers().stream().anyMatch(user -> user.getEmail().equals(Email)
                                                        && user.getPassword().equals(Password));
        return userExists;
    }

    @Override
    public boolean getUserById(Long Id){
        return getAllUsers().stream().anyMatch(user -> user.getId().equals(Id));
    }
}
