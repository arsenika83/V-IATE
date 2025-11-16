package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.User;
import com.AVASPP.VIATE.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        //this.userRepository.save(new User("111", "222", new String[] {"111", "222", "333"}));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        else {
            String[] fn = {"AAA", "AAA", "AAA"};
            userRepository.save(new User("AAA", "AAA", fn));
            return null;
        }

    }
}
