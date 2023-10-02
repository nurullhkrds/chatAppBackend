package com.nurullah.questapp.DTOs.response;

import com.nurullah.questapp.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private int avatar;
    private String userName;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.avatar = entity.getAvatar();
        this.userName = entity.getUserName();
    }
}
