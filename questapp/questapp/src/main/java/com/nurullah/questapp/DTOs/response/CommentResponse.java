package com.nurullah.questapp.DTOs.response;

import com.nurullah.questapp.entities.Comment;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {
    int id;
    int userId;
    int postId;
    String userName;
    String text;
    Date createDate;
    public CommentResponse(Comment entity) {
        this.id=entity.getId();
        this.createDate=entity.getCreateDate();
        this.userId=entity.getUser().getId();
        this.postId=entity.getPost().getId();
        this.userName=entity.getUser().getUserName();
        this.text=entity.getText();
    }
}
