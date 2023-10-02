package com.nurullah.questapp.bussiness.abstractt;

import com.nurullah.questapp.DTOs.request.CommentCreate;
import com.nurullah.questapp.DTOs.request.CommentUpdate;
import com.nurullah.questapp.DTOs.response.CommentResponse;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    DataResult<List<CommentResponse>> getAllComments(Optional<Integer> postId, Optional<Integer> userId);

    DataResult<Comment> getByIdComment(int commentId);

    DataResult<CommentResponse> addComment(CommentCreate commentCreate);

    Result updateComment(int commentId, CommentUpdate commentUpdate);

    Result deleteComment(int commnetId);
}
