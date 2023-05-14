package com.yrol.springbootrestfulwebservices.service.impl;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.entity.User;
import com.yrol.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.yrol.springbootrestfulwebservices.mapper.AutoUserMapper;
import com.yrol.springbootrestfulwebservices.mapper.UserMapper;
import com.yrol.springbootrestfulwebservices.repository.UserRepository;
import com.yrol.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    /**
     * Using the ModelMapper for DTO mapping. Not using @Autowire since the Bean is injected in SpringbootRestfulWebservicesApplication
     * **/
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        /**
         * Method 1: Custom mapper - UserMapper
         * Convert UserDto into User JPA entity, and Converting the User JPA to entity to UserDto
         * **/
//        User user = UserMapper.mapToUser(userDto);
//        User savedUser = userRepository.save(user);
//        return UserMapper.mapToUserDto(savedUser);

        /**
         * Method 2: Using ModelMapper library
         * Convert UserDto into User JPA entity, and Converting the User JPA to entity to UserDto
         * **/
//        User user = modelMapper.map(userDto, User.class);
//        User savedUser = userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class);

        /**
         * Method 3: Using MapStruct library
         * Convert UserDto into User JPA entity, and Converting the User JPA to entity to UserDto
         * **/
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);

        // throw custom exception - ResourceNotFoundException if user not found
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

//        return UserMapper.mapToUserDto(optionalUser.get()); // Using UserMapper custom mapper
//        return modelMapper.map(optionalUser.get(), UserDto.class); // using ModelMapper library
        return AutoUserMapper.MAPPER.mapToUserDto(user); // Using MapStruct library
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Method 1: Using custom mapper UserMapper
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

        // Method 2: Using ModelMapper library (lambda expression)
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

        // Method 3: Using MapStruct library
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
//        User existingUser = userRepository.findById(user.getId()).get();

        // throw custom exception - ResourceNotFoundException if user not found
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());

        // Method 1: Using custom mapper: UserMapper
//        return UserMapper.mapToUserDto(userRepository.save(existingUser));

        // Method 2: Using ModelMapper library
//        return modelMapper.map(userRepository.save(existingUser), UserDto.class);

        // Method 3: Using ModelMapper library
        return AutoUserMapper.MAPPER.mapToUserDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long userId) {

        // throw custom exception - ResourceNotFoundException if user not found
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
}
