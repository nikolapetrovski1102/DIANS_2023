package com.example.wineries.controllers;

import com.example.wineries.models.User;
import com.example.wineries.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Random;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String Login(@ModelAttribute User user, Model model, HttpServletResponse response,
                        HttpServletRequest request) {

        if (userService.loginUser(user.getEmail(), user.getPassword()))
        {
            User usr = userService.getUserByEmail(user.getEmail());
            setCookie(response, usr.getUsername());
            model.addAttribute("user", user.getName());
            return "redirect:/";
        }
        else{
            model.addAttribute("status", "User does not exist");
            return "Login";
        }

    }

    @GetMapping("/register")
    public String Register (){

        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        if (userService.checkUser(user.getEmail(), user.getUsername())){
            model.addAttribute("status", "User already exists");
                return "Register";
        }
        else{
            Random random = new Random();
            user.setId(random.nextLong());
            userService.addUser(user);
        }

        return "redirect:/login";
    }

    public void setCookie(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie("user", username);
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }

}
