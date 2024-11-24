package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.request.AuthReqDto;
import com.example.expense_tracker.dto.request.UserReqDto;
import com.example.expense_tracker.dto.response.UserResDto;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResDto createNewUser(UserReqDto userReq) {
        // Create User Object
        User userObj = new User();
        userObj.setName(userReq.getName());
        userObj.setEmailId(userReq.getEmailId());
        userObj.setPassword(encoder.encode(userReq.getPassword()));

        // Save user
        User newUser = userRepository.save(userObj);

        // prepare UserRes
        UserResDto userRes = new UserResDto();
        userRes.setId(newUser.getId());
        userRes.setName(newUser.getName());
        userRes.setEmailId(newUser.getEmailId());

        return userRes;
    }

    public void verify(@Valid AuthReqDto authReq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReq.getEmailId(), authReq.getPassword())
        );
    }
}
