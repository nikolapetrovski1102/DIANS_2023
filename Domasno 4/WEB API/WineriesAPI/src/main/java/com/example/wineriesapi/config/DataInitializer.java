package com.example.wineriesapi.config;

import com.example.wineriesapi.Model.Users;
import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Repository.UserRepository;
import com.example.wineriesapi.Repository.WineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    private final WineRepository wineRepository;
    private final UserRepository userRepository;

    public DataInitializer(WineRepository wineRepository, UserRepository userRepository) {
        this.wineRepository = wineRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        if(this.isEmptyWineries()){
            this.deleteAllDataWineries();
            this.addInitialDataWineries();
        }
        if (this.isEmptyUsers()){
            this.deleteAllDataUsers();
            this.addInitialDataUsers();
        }
    }

    public void addInitialDataWineries() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/updated_data.csv"));
            String line;
            Set<String> uniqueLocations = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data[3].equals("Winery") || data[3].equals("Vineyard")) {
                    try {
                        Wineries wineries = new Wineries(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[4], data[5], data[3]);
                        wineRepository.save(wineries);
                        uniqueLocations.add(data[5]);
                    } catch (NumberFormatException e) {
                        // Handle the exception specific to the 'data[5]' (address) field
                        System.err.println("Error parsing location: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInitialDataUsers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"));
            String line;
            Set<String> uniqueLocations = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (!data[0].equals("Id")) {
                    try {
                        Users users = new Users(data[1], data[2], data[3], data[4], data[5]);
                        userRepository.save(users);
                    } catch (NumberFormatException e) {
                        // Handle the exception specific to the 'data[5]' (address) field
                        System.err.println("Error parsing location: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isEmptyWineries() {
        return this.wineRepository.count() == 0;
    }

    public void deleteAllDataWineries() {
        this.wineRepository.deleteAll();
    }

    public boolean isEmptyUsers() {
        return this.userRepository.count() == 0;
    }

    public void deleteAllDataUsers() {
        this.userRepository.deleteAll();
    }

}
