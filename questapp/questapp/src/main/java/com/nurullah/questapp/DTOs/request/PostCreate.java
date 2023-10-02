package com.nurullah.questapp.DTOs.request;

import lombok.Data;

@Data
public class PostCreate {
    int id;
    String title;
    String text;
    int userId;
}
