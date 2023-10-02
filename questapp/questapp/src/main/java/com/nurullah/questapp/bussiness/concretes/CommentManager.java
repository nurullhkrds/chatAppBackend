package com.nurullah.questapp.bussiness.concretes;

import com.nurullah.questapp.DTOs.request.CommentCreate;
import com.nurullah.questapp.DTOs.request.CommentUpdate;
import com.nurullah.questapp.DTOs.response.CommentResponse;
import com.nurullah.questapp.bussiness.abstractt.ICommentService;
import com.nurullah.questapp.bussiness.abstractt.IPostService;
import com.nurullah.questapp.bussiness.abstractt.IUserService;
import com.nurullah.questapp.core.utilities.*;
import com.nurullah.questapp.entities.Comment;
import com.nurullah.questapp.entities.Post;
import com.nurullah.questapp.entities.User;
import com.nurullah.questapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentManager implements ICommentService {
    private final CommentRepository commentRepository;
    private final IUserService userService;
    private final IPostService postService;


    public CommentManager(CommentRepository commentRepository, IUserService userService, IPostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public DataResult<List<CommentResponse>> getAllComments(Optional<Integer> postId, Optional<Integer> userId) {
        List<Comment> list;
        if(postId.isPresent() && userId.isPresent()){

               list=commentRepository.findByPostIdAndUserId(postId,userId);
        }
        else if(postId.isPresent()){
            list=commentRepository.findByPostId(postId);

        }
        else if(userId.isPresent()){
            list=commentRepository.findByUserId(userId);
        }
        else{
            list=commentRepository.findAll();

        }
        return new DataResult<List<CommentResponse>>(true,"Data Getirildi",
                list.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList()));



    }

    @Override
    public DataResult<Comment> getByIdComment(int commentId) {
        Optional<Comment> testComment=commentRepository.findById(commentId);
        if (testComment.isPresent()){
            return new SuccesDataResult<Comment>(true,"Veri Getirildi",testComment.get());

        }
        return new ErrorDataResult<Comment>(false,"Veri Getirelemedi !",testComment.get());

    }

    @Override
    public DataResult<CommentResponse> addComment(CommentCreate commentCreate) {
        User userTest=userService.getUserById(commentCreate.getUserId()).getData();
        Post postTest=postService.getPostByIdOvverride(commentCreate.getPostId());
        if (userTest!=null && postTest!=null){
            Comment toAddedComment=new Comment();
            toAddedComment.setId(commentCreate.getId());
            toAddedComment.setUser(userTest);
            toAddedComment.setPost(postTest);
            toAddedComment.setText(commentCreate.getText());
            toAddedComment.setCreateDate(new Date());
            commentRepository.save(toAddedComment);
            CommentResponse commentResponse=new CommentResponse(toAddedComment);
            return new SuccesDataResult<CommentResponse>(true,"Yorum Başarıyla Eklendi...",commentResponse);
        }

        return new ErrorDataResult<CommentResponse>(false,"Yorum Eklenemedi !",null);
    }

    @Override
    public Result updateComment(int commentId, CommentUpdate commentUpdate) {
        Optional<Comment> commentTest=commentRepository.findById(commentId);
        if (commentTest.isPresent()){
            Comment toUpdateComment=commentTest.get();
            toUpdateComment.setText(commentUpdate.getText());
            commentRepository.save(toUpdateComment);
            return new SuccessResult(true,"Yorum Başarıyla Güncellendi");
        }
        return new ErrorResult(false,"Yorum güncellenemedi !");
    }

    @Override
    public Result deleteComment(int commnetId) {
        Optional<Comment> commentTest=commentRepository.findById(commnetId);
        if (commentTest.isPresent()){
            commentRepository.deleteById(commnetId);
            return new SuccessResult(true,"Yorum Başarıyla Silindi");
        }
        return new ErrorResult(false,"Yorum Silinemedi !");
    }
}
