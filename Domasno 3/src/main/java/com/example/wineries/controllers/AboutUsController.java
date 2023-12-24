package com.example.wineries.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    @GetMapping("/aboutUs")
    public String aboutUs (){

        return "About-us";
    }

}
