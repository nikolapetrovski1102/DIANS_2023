package com.example.wineriesapi.Controller;

import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Service.WineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WineriesController {

    private final WineService wineService;

    public WineriesController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/listWineries")
    public List<Wineries> listAll (){
        return ResponseEntity.ok().body(wineService.listWineries()).getBody();
    }



}
