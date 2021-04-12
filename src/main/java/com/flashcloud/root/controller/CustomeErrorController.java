package com.flashcloud.root.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomeErrorController implements ErrorController {

    @GetMapping
    public String getErrorPage(){
        return "error";
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}
