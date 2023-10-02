package com.nurullah.questapp.DTOs.request;

import lombok.Data;

@Data
public class LikeCreate {
    int id;
    int userId;
    int postId;
}
