package com.example.wineries.controllers;

import com.example.wineries.models.Wineries;
import com.example.wineries.services.WineryService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("")
public class HomeController {

    private WineryService wineryService;

    public HomeController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("")
    public String HomePage (Model model) {

        List<Wineries> wineriesList = wineryService.getWineries();

        model.addAttribute("wineires", wineriesList);

        model.addAttribute("allWineries", wineryService.getALlWineries());

        return "Home";
    }

    @GetMapping("/search")
    public String searchResult(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {

        model.addAttribute("allWineries",  wineryService.getALlWineries().stream().filter(wineries -> wineries.getWineryName().contains(searchQuery)));

        model.addAttribute("wineires", wineryService.getWineries());

        return "Home";
    }

}
