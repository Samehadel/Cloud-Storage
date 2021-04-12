package com.flashcloud.root.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    //private boolean display = false;

    @GetMapping
    public String getLogin(Authentication auth, Model model){

        return "login";
    }


}
