package com.example.wineries.controllers;

import com.example.wineries.models.User;
import com.example.wineries.models.Wineries;
import com.example.wineries.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Controller
public class UserController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String Login (Model model){

        model.addAttribute("user", null);

        return "Login";
    }

    @GetMapping("/logout")
    public String Logout (HttpServletResponse response, HttpServletRequest request){

        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/";

    }

    @PostMapping("/login")
    public String Login(@ModelAttribute User user, Model model, HttpServletResponse response) {

        String userAPIUrl = "http://localhost:8888/api/checkCredentials/" + user.getEmail() + "/" + user.getPassword();

        try {
            ResponseEntity<Boolean> responseApi = restTemplate.getForEntity(userAPIUrl, Boolean.class);

            if (Boolean.TRUE.equals(responseApi.getBody()))
            {
                setCookie(response, user.getUsername());
                model.addAttribute("user", user.getName());
                return "redirect:/";
            }
            else{
                model.addAttribute("status", "User does not exist");
                return "Login";
            }
        }
        catch (RestClientException e) {
            e.printStackTrace();

            model.addAttribute("status", "An error occurred");

            return "Login";
        }

    }

    @GetMapping("/register")
    public String Register (){

        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        String userAPIUrl = "http://localhost:8888/api/checkRegister/" + user.getEmail() + "/" + user.getUsername();

        try {
            ResponseEntity<Boolean> responseApi = restTemplate.getForEntity(userAPIUrl, Boolean.class);

            if (Boolean.TRUE.equals(responseApi.getBody())){
                model.addAttribute("status", "User already exists");
                    return "Register";
            }
            else{
                String createUserUrl = "http://localhost:8888/api/createNewUser";
                ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, user, String.class);
            }
            return "redirect:/login";
        }
        catch (RestClientException e) {
            e.printStackTrace();

            model.addAttribute("status", "An error occurred");

            return "Login";
        }
    }

    public void setCookie(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie("user", username);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }

}
