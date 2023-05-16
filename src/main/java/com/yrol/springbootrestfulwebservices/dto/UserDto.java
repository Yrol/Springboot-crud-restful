package com.yrol.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "user first name should not be null or empty") // overriding the default bean error message
    private String firstName;

    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "user email should not be null or empty")
    @Email
    private String email;
}
