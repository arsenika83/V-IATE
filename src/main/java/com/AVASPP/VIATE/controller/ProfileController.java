package com.AVASPP.VIATE.controller;

import com.AVASPP.VIATE.entity.Profile;
import com.AVASPP.VIATE.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id) {
        Profile profile = profileService.save(id);

        model.addAttribute("profile", profile);

        return "profile";
    }


}
