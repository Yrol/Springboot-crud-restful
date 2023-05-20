package com.yrol.springbootrestfulwebservices.controller;

import com.yrol.springbootrestfulwebservices.dto.UserDto;
import com.yrol.springbootrestfulwebservices.exception.ErrorDetails;
import com.yrol.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.yrol.springbootrestfulwebservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "Create User, Update User, Get User, Get All Users & Delete User"
)

/**
 * Controller calling the CRUD operation for User
 * Consist of Bean validation for create and update
 * **/

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {


    // Doesn't need to user @Autowired annotation for DI since this class consist of only one parameter and SpringBoot will automatically inject it.
    private UserService userService;


    /**
     * User creation POST REST API
     * [POST] http://localhost:8080/api/users
     * Using @Operation and @ApiResponse for Swagger
     **/

    @Operation(
            summary = "Create User REST API",
            description = "Creating users using basic information (firstname, lastname and email)"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Get user by ID REST API
     * [GET] http://localhost:8080/api/users/1
     * Using @Operation and @ApiResponse for Swagger
     * **/

    @Operation(
            summary = "Get User REST API",
            description = "Fetching users by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 CREATED"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users
     * [GET] http://localhost:8080/api/users
     * Using @Operation and @ApiResponse for Swagger
     * **/

    @Operation(
            summary = "Get all users REST API",
            description = "Fetching all users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 CREATED"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Update existing user REST API
     * [PATCH] http://localhost:8080/api/users/1
     * Using @Operation and @ApiResponse for Swagger
     */
    @Operation(
            summary = "Update User REST API",
            description = "Update users by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 CREATED"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("id") Long userId, @RequestBody UserDto user) {
        user.setId(userId); // setting the user using the parameterised ID
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete an existing user by ID
     * [DELETE] http://localhost:8080/api/users/1
     * Using @Operation and @ApiResponse for Swagger
     * **/
    @Operation(
            summary = "Delete User REST API",
            description = "Delete users by ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP Status 204 CREATED"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    /**
     * Handling controller specific exceptions. Ex: handling custom ResourceNotFoundException with a custom message
     * **/
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
