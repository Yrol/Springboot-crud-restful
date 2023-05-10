package com.yrol.springbootrestfulwebservices.service;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
