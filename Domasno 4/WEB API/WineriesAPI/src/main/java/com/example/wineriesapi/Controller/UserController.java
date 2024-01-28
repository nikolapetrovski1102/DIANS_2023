package com.example.wineriesapi.Controller;

import com.example.wineriesapi.Model.Users;
import com.example.wineriesapi.Model.Wineries;
import com.example.wineriesapi.Repository.UserRepository;
import com.example.wineriesapi.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkCredentials/{email}/{password}")
    public boolean checkCredentials (@PathVariable String email,
                                   @PathVariable String password) {

        return userService.checkCredentials(email, password);
    }

    @GetMapping("/checkRegister/{email}/{username}")
    public boolean userExists (@PathVariable String email,
                               @PathVariable String username){

        return userService.userExists(email, username);
    }

    @PostMapping("/createNewUser")
    public void register (@RequestBody Users user){
        userService.create(user);
    }

}
