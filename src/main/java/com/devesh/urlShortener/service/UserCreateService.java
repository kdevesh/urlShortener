package com.devesh.urlShortener.service;

import com.devesh.urlShortener.exceptions.DuplicateException;
import com.devesh.urlShortener.exceptions.SaveException;
import com.devesh.urlShortener.models.User;
import com.devesh.urlShortener.models.UserDTO;
import com.devesh.urlShortener.models.UserKey;
import com.devesh.urlShortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class UserCreateService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserDTO userDTO) {
        UserKey userKey = UserKey.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName()).build();
        User user = User.builder()
                .userKey(userKey)
                .createdOn(LocalDateTime.now())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword())).build();

        boolean exists = userRepository.existsByUserKeyEmail(userKey.getEmail());
        if (exists)
            throw new DuplicateException("User already exits with " + userKey.getEmail() + " mail-id");
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new SaveException("User couldn't be created: " + e.getMessage());
        }
    }
}
