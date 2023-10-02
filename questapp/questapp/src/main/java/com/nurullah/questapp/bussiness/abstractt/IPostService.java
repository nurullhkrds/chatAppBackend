package com.nurullah.questapp.bussiness.abstractt;

import com.nurullah.questapp.DTOs.request.PostCreate;
import com.nurullah.questapp.DTOs.request.PostUpdate;
import com.nurullah.questapp.DTOs.response.PostResponse;
import com.nurullah.questapp.entities.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    List<PostResponse> getAllPosts(Optional<Integer> userId);

    PostResponse getPostById(int postId);

    Post getPostByIdOvverride(int postId);

    PostResponse addPost(PostCreate postCreate);

    Post updatePost(int postId, PostUpdate postUpdate);

    void deletePost(int postId);
}
