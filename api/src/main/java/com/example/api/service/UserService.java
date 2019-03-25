package com.example.api.service;


import com.example.api.exception.CustomException;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import com.example.api.security.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    protected AuthenticationManager authenticationManager ;

    public String signin(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username, user.password));
            return jwtTokenProvider.createToken(user.username, userRepository.findByUsername(user.username).roles);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.username)) {
            user.password = passwordEncoder.encode(user.password);
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.username, user.roles);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deleteAll() {
      userRepository.deleteAll();
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).roles);
    }

}
