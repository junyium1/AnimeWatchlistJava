package com.animewatchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControleurWeb {
    
    @GetMapping("/")
    public String accueil() {
        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/seiyuus")
    public String seiyuus() {
        return "seiyuus";
    }
    
    @GetMapping("/studios")
    public String studios() {
        return "studios";
    }
} 