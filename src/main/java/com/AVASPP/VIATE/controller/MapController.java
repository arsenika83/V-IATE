package com.AVASPP.VIATE.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MapController {

    @GetMapping("/map")
    public String map(HttpSession session, Model model) {
        return "map";
    }
}
