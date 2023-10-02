package com.nurullah.questapp.repository;

import com.nurullah.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer> {
    List<Like> findByPostIdAndUserId(Optional<Integer> postId, Optional<Integer> userId);

    List<Like> findByPostId(Optional<Integer> postId);

    List<Like> findByUserId(Optional<Integer> userId);

    @Query(value = "select 'gönderinizi beğendi',likes.post_id,users.avatar,users.username " +
            "from likes  left join users on users.id=likes.user_id " +
            "where likes.post_id in :postIds",nativeQuery = true)
    List<Object> findUserTopLikes(@Param("postIds")List<Integer>postIds);
}
