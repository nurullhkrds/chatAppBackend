package com.nurullah.questapp.api;

import com.nurullah.questapp.DTOs.request.CommentCreate;
import com.nurullah.questapp.DTOs.request.CommentUpdate;
import com.nurullah.questapp.DTOs.response.CommentResponse;
import com.nurullah.questapp.bussiness.abstractt.ICommentService;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/comments")
public class CommentsControllers {

  private final ICommentService commentService;

    public CommentsControllers(ICommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public DataResult<List<CommentResponse>>getAllComments
            (@RequestParam Optional<Integer> postId,@RequestParam Optional<Integer> userId){
       return  commentService.getAllComments(postId,userId);

    }

    @GetMapping("/{commentId}")
    public DataResult<Comment> getByIdComment(@PathVariable int commentId){
        return commentService.getByIdComment(commentId);
    }

    @PostMapping
    public DataResult<CommentResponse> addComment(@RequestBody CommentCreate commentCreate){
        return commentService.addComment(commentCreate);
    }

    @PutMapping("/{commentId}")
    public Result updateComment(@PathVariable int commentId,@RequestBody CommentUpdate commentUpdate){
        return commentService.updateComment(commentId,commentUpdate);
    }
    @DeleteMapping("/{commnetId}")
    public Result deleteComment(@PathVariable int commnetId){
        return commentService.deleteComment(commnetId);
    }

}
