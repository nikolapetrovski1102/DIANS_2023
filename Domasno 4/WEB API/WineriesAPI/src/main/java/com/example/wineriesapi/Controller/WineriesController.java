package com.example.wineriesapi.Controller;

import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Service.WineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.ok;

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

    @GetMapping("listWineries/{searchQuery}")
    public List<Wineries> searchWineries (@PathVariable String searchQuery){
        if (searchQuery != ""){
            List<Wineries> wineriesList = wineService.listWineries().stream().filter(wineries -> wineries.getWineryName().contains(searchQuery)).toList();
            return ResponseEntity.ok(wineriesList).getBody();
        }
        else{
            return (List<Wineries>) ResponseEntity.notFound();
        }
    }

    @GetMapping("/bestWineries")
    public List<Wineries> bestWineries () {
        return ResponseEntity.ok(wineService.bestWineries()).getBody();
    }

}
