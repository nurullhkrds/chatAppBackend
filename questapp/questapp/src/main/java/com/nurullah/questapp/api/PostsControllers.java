package com.nurullah.questapp.api;

import com.nurullah.questapp.DTOs.request.PostCreate;
import com.nurullah.questapp.DTOs.request.PostUpdate;
import com.nurullah.questapp.DTOs.response.PostResponse;
import com.nurullah.questapp.bussiness.abstractt.IPostService;
import com.nurullah.questapp.entities.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/posts")
public class PostsControllers {

    private final IPostService postService;

    public PostsControllers(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<PostResponse>getAllPosts(@RequestParam Optional<Integer> userId){
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public PostResponse getPostById(@PathVariable int postId){
        return postService.getPostById(postId);
    }


    @PostMapping
    public PostResponse addPost(@RequestBody PostCreate postCreate){
        return postService.addPost(postCreate);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable int postId,@RequestBody PostUpdate postUpdate){
        return postService.updatePost(postId,postUpdate);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable int postId){
         postService.deletePost(postId);
    }
}
