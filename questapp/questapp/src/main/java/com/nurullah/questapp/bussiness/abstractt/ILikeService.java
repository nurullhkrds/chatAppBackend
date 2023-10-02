package com.nurullah.questapp.bussiness.abstractt;


import com.nurullah.questapp.DTOs.request.LikeCreate;
import com.nurullah.questapp.DTOs.response.LikeResponse;
import com.nurullah.questapp.core.utilities.DataResult;
import com.nurullah.questapp.core.utilities.Result;
import com.nurullah.questapp.entities.Like;

import java.util.List;
import java.util.Optional;

public interface ILikeService {
    DataResult<List<LikeResponse>> getAllLikes(Optional<Integer> postId, Optional<Integer> userId);

    DataResult<LikeResponse> getLikeById(int likeId);

    DataResult<LikeResponse> addLike(LikeCreate likeCreate);

    DataResult<LikeResponse> deleteLike(int likeId);

}
