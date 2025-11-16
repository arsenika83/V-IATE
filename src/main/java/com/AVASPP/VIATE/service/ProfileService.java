package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Profile;
import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final UserService userService;

    public ProfileService(ProfileRepository profileRepository, UserService userRepository) {
        this.profileRepository = profileRepository;
        this.userService = userRepository;
    }

    public Profile save(User user) {
        if(profileRepository.findById(user.getId()).isPresent()) return profileRepository.findById(user.getId()).get(); //если такой профиль уже существует - его не нужно сохранять
        else return profileRepository.save(new Profile(user));
    }

    public Profile save(long id) {
        User user = userService.findById(id);
        if(profileRepository.findById(user.getId()).isPresent()) return profileRepository.findById(user.getId()).get();
        else return profileRepository.save(new Profile(user));
    }
}
