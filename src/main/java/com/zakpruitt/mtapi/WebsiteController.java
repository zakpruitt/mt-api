package com.zakpruitt.mtapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

    @GetMapping
    public String Home(Model model) {
        return "index";
    }
}