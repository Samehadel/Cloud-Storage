package com.flashcloud.root.controller;

import com.flashcloud.root.model.User;
import com.flashcloud.root.services.UserService;
import com.flashcloud.root.utils.UserInputsChecker;
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

        //Check Inputs Sizes
        if(!UserInputsChecker.checkSignUp(user)){
            model.addAttribute("signupMessage", "Invalid Credentials");
            return "signup";
        }

        //Check If User Exists
        if(userService.getUser(user.getUsername()) != null){
            model.addAttribute("signupMessage", "Username already exist");
            return "signup";
        }else{
            userService.createUser(user);
            model.addAttribute("userMessage", "Account created successfully, please login");
            return "login";
        }
    }

}
