package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if(user != null) {
            model.addAttribute("user_name", user.getFull_name()[0]);
            model.addAttribute("login", user.getLogin());
        }
        else {
            return "redirect:/login";
        }

        return "index";
    }

}
