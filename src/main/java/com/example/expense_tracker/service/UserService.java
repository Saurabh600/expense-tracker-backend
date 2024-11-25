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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public List<UserResDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResDto::from)
                .collect(Collectors.toList());
    }

    public User getUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId).orElseThrow();
    }

    public UserResDto getUserById(long userId) {
        return UserResDto.from(userRepository.findById(userId).orElseThrow());
    }

    public UserResDto createNewUser(UserReqDto userReq) {
        User userObj = UserReqDto.toUser(userReq);
        userObj.setPassword(encoder.encode(userReq.getPassword()));
        return UserResDto.from(userRepository.save(userObj));
    }

    public void verify(@Valid AuthReqDto authReq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReq.getEmailId(), authReq.getPassword())
        );
    }
}
