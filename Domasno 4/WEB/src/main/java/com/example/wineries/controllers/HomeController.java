package com.example.wineries.controllers;

import com.example.wineries.models.Wineries;
import com.example.wineries.services.WineryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    private final WineryService wineryService;
    private final RestTemplate restTemplate;

    public HomeController(WineryService wineryService, RestTemplate restTemplate) {
        this.wineryService = wineryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String HomePage (Model model) {

        String winesApiUrl = "http://localhost:8888/api/";

        ResponseEntity<Wineries[]> response = restTemplate.getForEntity(winesApiUrl + "bestWineries", Wineries[].class);
        model.addAttribute("wineries", Arrays.asList(response.getBody()));

        response = restTemplate.getForEntity(winesApiUrl + "listWineries", Wineries[].class);
        model.addAttribute("allWineries", Arrays.asList(response.getBody()));

        return "Home";
    }

    @GetMapping("/search")
    public String searchResult(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {

        String winesApiUrl = "http://localhost:8888/api/";

        ResponseEntity<Wineries[]> response = restTemplate.getForEntity(winesApiUrl + "listWineries?searchQuery=" + searchQuery, Wineries[].class);
        model.addAttribute("allWineries",  Arrays.asList(response.getBody()));

        response = restTemplate.getForEntity(winesApiUrl + "bestWineries", Wineries[].class);
        model.addAttribute("wineries", Arrays.asList(response.getBody()));

        return "Home";
    }

}
