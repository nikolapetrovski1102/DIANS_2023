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
    private final String winesApiUrl = "http://localhost:8888/api/";

    public HomeController(WineryService wineryService, RestTemplate restTemplate) {
        this.wineryService = wineryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String homePage(Model model) {
        List<Wineries> bestWineries = getWineries("bestWineries");
        List<Wineries> allWineries = getWineries("listWineries");

        model.addAttribute("wineries", bestWineries);
        model.addAttribute("allWineries", allWineries);

        return "Home";
    }

    @GetMapping("/search")
    public String searchResult(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
        List<Wineries> allWineries = getWineries("listWineries?searchQuery=" + searchQuery);
        List<Wineries> bestWineries = getWineries("bestWineries");

        model.addAttribute("allWineries", allWineries);
        model.addAttribute("wineries", bestWineries);

        return "Home";
    }

    private List<Wineries> getWineries(String endpoint) {
        ResponseEntity<Wineries[]> response = restTemplate.getForEntity(winesApiUrl + endpoint, Wineries[].class);
        return Arrays.asList(response.getBody());
    }
}