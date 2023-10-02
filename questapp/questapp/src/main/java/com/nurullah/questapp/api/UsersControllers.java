package com.nurullah.questapp.api;


import com.nurullah.questapp.DTOs.request.UserUpdateRequest;
import com.nurullah.questapp.DTOs.response.UserResponse;
import com.nurullah.questapp.bussiness.abstractt.IUserService;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.Comment;
import com.nurullah.questapp.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UsersControllers {
    private final IUserService userService;

    public UsersControllers(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public DataResult<List<User>> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public DataResult<UserResponse> getById(@PathVariable int userId){
        return userService.getOneUserById(userId);

    }

    @PostMapping
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);

    }


    @PutMapping("/{userId}")
    public DataResult<User> updateUser(@PathVariable int userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(userId,userUpdateRequest);

    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable int userId){
         return userService.deleteUser(userId);

    }
    @GetMapping("/activity/{userId}")
    public List<Object> getUserActivity(@PathVariable int userId) {
        return userService.getUserActivity(userId);
    }

}
