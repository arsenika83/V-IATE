package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Optional<User> userSearch = userRepository.findByLogin(login);

        if ((userSearch.isPresent())) {
            User user = userSearch.get();

            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);

                return "redirect:/index";
            } else {
                model.addAttribute("error", "Неверный пароль");
            }
        } else {
            model.addAttribute("error", "Пользователь не найден");
        }

        return "login";
    }

    @GetMapping("/index")
    public String indexPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");

        if (user == null){
            return "redirect:/login";
        }

        model.addAttribute("login", user.getLogin());

        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
