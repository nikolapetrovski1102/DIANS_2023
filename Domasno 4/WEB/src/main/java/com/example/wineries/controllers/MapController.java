package com.example.wineries.controllers;

import com.example.wineries.WineriesApplication;
import com.example.wineries.models.Wineries;
import com.example.wineries.services.WineryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class MapController {
    private WineryService wineryService;
    private final RestTemplate restTemplate;


    public MapController(WineryService wineryService, RestTemplate restTemplate) {
        this.wineryService = wineryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/map")
    public String map(@RequestParam(name = "searchQuery", required = false) String searchQuery,
                      Model model,
                      HttpServletRequest request) {

        String winesApiUrl = "http://localhost:8888/api/listWineries";

        if (searchQuery != null) {
            model.addAttribute("wineires", wineryService.getALlWineries().stream().filter(wineries -> wineries.getWineryName().contains(searchQuery)));
        } else {
            try {
                ResponseEntity<Wineries[]> response = restTemplate.getForEntity(winesApiUrl, Wineries[].class);
                model.addAttribute("wineires", Arrays.asList(response.getBody()));
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }

        return "Map";
    }

}
