package com.example.wineries.controllers;

import com.example.wineries.services.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {
    private WineryService wineryService;

    public MapController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("/map")
    public String map(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {

        if (searchQuery != null){
            model.addAttribute("wineires",  wineryService.getALlWineries().stream().filter(wineries -> wineries.getWineryName().contains(searchQuery)));
        }else{
            model.addAttribute("wineires", wineryService.getALlWineries());
        }

        return "Map";
    }

}
