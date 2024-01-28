package com.example.wineriesapi.Service.impl;

import com.example.wineriesapi.Repository.WineRepository;
import com.example.wineriesapi.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final WineRepository wineRepository;

    public UserServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

}
