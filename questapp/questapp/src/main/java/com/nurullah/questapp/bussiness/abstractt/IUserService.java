package com.nurullah.questapp.bussiness.abstractt;

import com.nurullah.questapp.DTOs.request.UserUpdateRequest;
import com.nurullah.questapp.DTOs.response.UserResponse;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.User;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    DataResult<List<User>> getAllUser();
    DataResult<User> getUserById(int id);
    DataResult<User> getByUserName(String userName);
    DataResult<UserResponse> getOneUserById(int id);
    Result addUser(User user);

    Result deleteUser(int id);


    List<Object> getUserActivity(int userId);

    DataResult<User> updateUser(int userId, UserUpdateRequest userUpdateRequest);
}
