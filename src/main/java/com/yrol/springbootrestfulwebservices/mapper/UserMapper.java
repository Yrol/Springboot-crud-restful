package com.yrol.springbootrestfulwebservices.mapper;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.entity.User;

/**
 * lass for converting JPA entities to DTOs and vice versa
 * **/

public class UserMapper {

    // Converting User JPA entity into UserDto
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    // Converting UserDto into JPA Entity
    public static User mapToUser(UserDto userDto) {
        return new User(
            userDto.getId(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail()
        );
    }
}
