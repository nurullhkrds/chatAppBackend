package com.nurullah.questapp.DTOs.response;

import com.nurullah.questapp.entities.Post;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostResponse {
    int id;
    int userId;
    String userName;
    String text;
    String title;
    Date createDate;
    List<LikeResponse> postLikes;


    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.createDate=entity.getCreateDate();
        this.userName=entity.getUser().getUserName();
        this.text=entity.getText();
        this.title=entity.getTitle();
        this.postLikes=likes;
    }
}
