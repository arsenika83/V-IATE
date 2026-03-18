package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.AnnouncementService;
import com.AVASPP.VIATE.service.NewsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user_name", user.getFull_name()[0]);
            model.addAttribute("login", user.getLogin());

            model.addAttribute("news", newsService.getLastArticles(10));
            model.addAttribute("announcements", announcementService.getLastAnnouncements(20));
        }
        else {
            return "redirect:/login";
        }

        return "index";
    }
}
