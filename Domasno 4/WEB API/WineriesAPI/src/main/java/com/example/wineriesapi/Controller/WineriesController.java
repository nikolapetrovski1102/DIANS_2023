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

    @GetMapping("/all")
    public ResponseEntity<List<Wineries>> listAllWineries() {
        return ResponseEntity.ok(wineService.listWineries());
    }

    @GetMapping("/search/{searchQuery}")
    public ResponseEntity<List<Wineries>> searchWineries(@PathVariable String searchQuery) {
        if (!searchQuery.isEmpty()) {
            List<Wineries> wineriesList = wineService.listWineries().stream()
                    .filter(wineries -> wineries.getWineryName().contains(searchQuery))
                    .toList();
            return ResponseEntity.ok(wineriesList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/best")
    public ResponseEntity<List<Wineries>> getBestWineries() {
        return ResponseEntity.ok(wineService.bestWineries());
    }
}
