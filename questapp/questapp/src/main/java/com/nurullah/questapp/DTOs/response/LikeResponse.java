package com.nurullah.questapp.DTOs.response;

import com.nurullah.questapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    int id;
    int userId;
    int postId;
    String userName;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
        this.userName=entity.getUser().getUserName();
    }
}