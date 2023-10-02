package com.nurullah.questapp.bussiness.concretes;

import com.nurullah.questapp.DTOs.request.LikeCreate;
import com.nurullah.questapp.DTOs.response.LikeResponse;
import com.nurullah.questapp.bussiness.abstractt.ILikeService;
import com.nurullah.questapp.bussiness.abstractt.IPostService;
import com.nurullah.questapp.bussiness.abstractt.IUserService;
import com.nurullah.questapp.core.utilities.*;
import com.nurullah.questapp.entities.Like;
import com.nurullah.questapp.entities.Post;
import com.nurullah.questapp.entities.User;
import com.nurullah.questapp.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeManager implements ILikeService {
    private final LikeRepository likeRepository;
    private final IUserService userService;
    private final IPostService postService;

    public LikeManager(LikeRepository likeRepository, IUserService userService, IPostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public DataResult<List<LikeResponse>> getAllLikes(Optional<Integer> postId, Optional<Integer> userId) {
        List<Like> list;
        if (postId.isPresent() && userId.isPresent()){
            list=likeRepository.findByPostIdAndUserId(postId,userId);

        }
        else if(postId.isPresent()){
            list=likeRepository.findByPostId(postId);
        }
        else if (userId.isPresent()) {
            list=likeRepository.findByUserId(userId);
        }
        else{
            list=likeRepository.findAll();
        }
        return new SuccesDataResult<List<LikeResponse>>(true,"Data getirildi",
                list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList())
                );

    }

    @Override
    public DataResult<LikeResponse> getLikeById(int likeId) {
        Optional<Like> likeTest=likeRepository.findById(likeId);
        if(likeTest.isPresent()){
            return new SuccesDataResult<LikeResponse>(true,"Data Getirildi",new LikeResponse(likeTest.get()));
        }
        return new ErrorDataResult<LikeResponse>(false,"Data Getirelemedi !",new LikeResponse(likeTest.get()));
    }

    @Override
    public DataResult<LikeResponse> addLike(LikeCreate likeCreate) {
        User userTest=userService.getUserById(likeCreate.getUserId()).getData();
        Post postTest=postService.getPostByIdOvverride(likeCreate.getPostId());
        if(userTest!=null&&postTest!=null){
            Like toSaveLike=new Like();
            toSaveLike.setId(likeCreate.getId());
            toSaveLike.setUser(userTest);
            toSaveLike.setPost(postTest);
            likeRepository.save(toSaveLike);
            return new SuccesDataResult<LikeResponse>(true,"Eklendi...",new LikeResponse(toSaveLike));
        }
        return  new ErrorDataResult<LikeResponse>(false,"Eklenemedi !...",null);
    }

    @Override
    public DataResult<LikeResponse> deleteLike(int likeId) {
        Optional<Like> likeTest=likeRepository.findById(likeId);
        if (likeTest.isPresent()){
            likeRepository.deleteById(likeId);
            return new SuccesDataResult<LikeResponse>(true,"Başarıyla Silindi...",new LikeResponse(likeTest.get()));
        }
        return new ErrorDataResult<LikeResponse>(false,"Silinemedi !...",null);
    }

}
