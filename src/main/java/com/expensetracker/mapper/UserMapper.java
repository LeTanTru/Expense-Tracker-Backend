package com.expensetracker.mapper;

import com.expensetracker.dto.request.UpdateUserRequest;
import com.expensetracker.dto.request.UserCreationRequest;
import com.expensetracker.dto.response.UserResponse;
import com.expensetracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UpdateUserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

}
