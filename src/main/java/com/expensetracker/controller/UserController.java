package com.expensetracker.controller;

import com.expensetracker.dto.request.UpdateUserRequest;
import com.expensetracker.dto.request.UserCreationRequest;
import com.expensetracker.dto.response.ApiResponse;
import com.expensetracker.dto.response.UserResponse;
import com.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse user = userService.createUser(request);

        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("User created!")
                .result(user)
                .build();
    }


    @GetMapping("/users")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("User list got !")
                .result(users).build();

    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") String id) {
        UserResponse user = userService.getUserById(id);

        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("User got by id")
                .result(user)
                .build();
    }

    @PutMapping("/user/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("id") String id, @RequestBody @Valid UpdateUserRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("User updated !")
                .result(user)
                .build();
    }

    @DeleteMapping("/user/{id}")
    public ApiResponse<UserResponse> deleteUserById(@PathVariable("id") String id) {
        UserResponse user = userService.deleteUserById(id);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("User deleted !")
                .result(user)
                .build();
    }
}
