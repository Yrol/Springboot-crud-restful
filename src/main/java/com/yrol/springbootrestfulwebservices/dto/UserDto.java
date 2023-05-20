package com.yrol.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for user details
 * Using @Schema for Swagger
 * **/

@Schema(
        description = "UserDto Model information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @Schema(
            description = "User first name"
    )
    @NotEmpty(message = "user first name should not be null or empty") // overriding the default bean error message
    private String firstName;

    @Schema(
            description = "User last name"
    )
    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    @Schema(
            description = "User emails address"
    )
    @NotEmpty(message = "user email should not be null or empty")
    @Email
    private String email;
}
