package com.nurullah.questapp.DTOs.request;

import lombok.Data;

@Data
public class CommentCreate {
    int id;
    int postId;
    int userId;
    String text;
}
