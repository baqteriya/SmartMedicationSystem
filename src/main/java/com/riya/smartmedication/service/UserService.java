package com.riya.smartmedication.service;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.riya.smartmedication.entity.User;
import com.riya.smartmedication.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public User loginUser(String email, String password) {

        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        return user.orElse(null);
    }
}