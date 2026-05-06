package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.News;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.service.NewsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/news/{id}")
    public String news(HttpSession session, Model model, @PathVariable Long id) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user_name", user.getFull_name()[1]);
            model.addAttribute("login", user.getLogin());
        }

        News article = newsService.findById(id);
        String edited_text = article.getText().replace("\\n", "\n");
        model.addAttribute("article", article);
        model.addAttribute("article_text", edited_text);

        return "news";
    }
}
