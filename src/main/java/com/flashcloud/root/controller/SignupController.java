package com.flashcloud.root.controller;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSignup(@ModelAttribute User user, Model model){
        return "signup";
    }

    @PostMapping
    public String submit(@ModelAttribute User user, Model model){

        String userMessage = "";

        if(userService.getUser(user.getUsername()) != null){
            userMessage = "Username already exist";
        }else{
            userService.createUser(user);
            userMessage = "Account created successfully, please login";
        }

        model.addAttribute("userMessage", userMessage);

        return "signup";
    }

}
