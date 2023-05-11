package com.yrol.springbootrestfulwebservices.service.impl;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.entity.User;
import com.yrol.springbootrestfulwebservices.mapper.UserMapper;
import com.yrol.springbootrestfulwebservices.repository.UserRepository;
import com.yrol.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        // Converting the User JPA to entity to UserDto and return
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) throws NoSuchElementException {
        Optional<User> optionalUser = userRepository.findById(userId);

        try {
            return UserMapper.mapToUserDto(optionalUser.get());
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return UserMapper.mapToUserDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
