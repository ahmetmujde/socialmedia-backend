package com.socialmedia.service;

import com.socialmedia.entity.User;
import com.socialmedia.exception.PasswordNotMatchRegexException;
import com.socialmedia.exception.UsernameAlreadyExistException;
import com.socialmedia.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    //PasswordEncoder from Spring security and encodes the user's password
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void createUser(User user) {
       if (userRepository.existByUsername(user.getUsername())) {
           throw new UsernameAlreadyExistException();
       }

        if (userRepository.existByUsername(user.getPassword())) {
            throw new UsernameAlreadyExistException();
        }


        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        String userPassword = user.getPassword();

        if (!pattern.matcher(userPassword).find()) {
            throw new PasswordNotMatchRegexException();
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
}
