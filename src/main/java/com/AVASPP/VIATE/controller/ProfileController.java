package com.AVASPP.VIATE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(@RequestParam(name = "name", required = false, defaultValue = "no_name") String name,
                          @RequestParam(name = "group", required = false, defaultValue = "ИС2-Б24") String group, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("group", group);
        return "profile";
    }


}
