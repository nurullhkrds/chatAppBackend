package com.nurullah.questapp.api;

import com.nurullah.questapp.DTOs.request.LikeCreate;
import com.nurullah.questapp.DTOs.response.LikeResponse;
import com.nurullah.questapp.bussiness.abstractt.ILikeService;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.Like;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/likes")
public class LikesControllers {
    private final ILikeService likeService;

    public LikesControllers(ILikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public DataResult<List<LikeResponse>> getAllLikes(@RequestParam Optional<Integer> postId, @RequestParam Optional<Integer> userId){
        return likeService.getAllLikes(postId,userId);
    }
    @GetMapping("/{likeId}")
    public DataResult<LikeResponse> getLikeById(@PathVariable int likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public  DataResult<LikeResponse> addLike(@RequestBody LikeCreate likeCreate){
        return likeService.addLike(likeCreate);
    }

    @DeleteMapping("/{likeId}")
    public DataResult<LikeResponse> deleteLike(@PathVariable int likeId){
        return likeService.deleteLike(likeId);

    }


}
