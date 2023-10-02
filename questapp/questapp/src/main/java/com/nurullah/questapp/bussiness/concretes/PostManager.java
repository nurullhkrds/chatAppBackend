package com.nurullah.questapp.bussiness.concretes;

import com.nurullah.questapp.DTOs.request.PostCreate;
import com.nurullah.questapp.DTOs.request.PostUpdate;
import com.nurullah.questapp.DTOs.response.LikeResponse;
import com.nurullah.questapp.DTOs.response.PostResponse;
import com.nurullah.questapp.bussiness.abstractt.ILikeService;
import com.nurullah.questapp.bussiness.abstractt.IPostService;
import com.nurullah.questapp.bussiness.abstractt.IUserService;
import com.nurullah.questapp.entities.Post;
import com.nurullah.questapp.entities.User;
import com.nurullah.questapp.repository.PostRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostManager implements IPostService {

    private final PostRepository postRepository;
    private final IUserService userService;
    private final ILikeService likeService;

    @Lazy
    public PostManager(PostRepository postRepository, IUserService userService, ILikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
    }



    @Override
    public List<PostResponse> getAllPosts(Optional<Integer> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }
        else{
            list = postRepository.findAll();
        }

        return list.stream().map(post -> {
            List<LikeResponse> likes= likeService.getAllLikes(Optional.of(post.getId()), Optional.ofNullable(null)).getData();
            return new PostResponse(post,likes);}).collect(Collectors.toList());





    }

    @Override
    public PostResponse getPostById(int postId) {
        Optional<Post> post=postRepository.findById(postId);
        List<LikeResponse> likes= likeService.getAllLikes(Optional.of(postId), Optional.ofNullable(null)).getData();
         return new PostResponse(post.get(),likes);


    }
    @Override
    public Post getPostByIdOvverride(int postId) {
        return postRepository.findById(postId).orElse(null);

    }

    @Override
    public PostResponse addPost(PostCreate postCreate) {
        User user=userService.getUserById(postCreate.getUserId()).getData();
        if(user==null){
            return null;
        }
        Post toSave=new Post();
        toSave.setId(postCreate.getId());
        toSave.setText(postCreate.getText());
        toSave.setTitle(postCreate.getTitle());
        toSave.setUser(user);
        toSave.setCreateDate(new Date());
        postRepository.save(toSave);
        List<LikeResponse> likes= likeService.getAllLikes(Optional.of(toSave.getId()), Optional.ofNullable(null)).getData();
        return new PostResponse(toSave,likes);

    }

    @Override
    public Post updatePost(int postId, PostUpdate postUpdate) {
        Optional<Post>isHavePost=postRepository.findById(postId);
        if(isHavePost.isPresent()){
            Post toUpdate=isHavePost.get();
            toUpdate.setText(postUpdate.getText());
            toUpdate.setTitle(postUpdate.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    @Override
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }


}
