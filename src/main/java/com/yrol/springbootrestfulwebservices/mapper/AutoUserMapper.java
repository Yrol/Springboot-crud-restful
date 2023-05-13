package com.yrol.springbootrestfulwebservices.mapper;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface for MapStruct.
 * No implementation is required for this as MapStruct will create the implementation for this interface at compilation time.
 * The entities must have the same name in both DTO and JPA. Otherwise, need to map manually, ex: @Mapping(source = "email", target = "emailAddress")
 * **/

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class); // provide implementation at compilation time

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
