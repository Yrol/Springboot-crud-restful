package com.yrol.springbootrestfulwebservices.service.impl;

import com.yrol.springbootrestfulwebservices.entity.User;
import com.yrol.springbootrestfulwebservices.repository.UserRepository;
import com.yrol.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
