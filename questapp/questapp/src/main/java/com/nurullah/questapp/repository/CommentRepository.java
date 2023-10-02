package com.nurullah.questapp.repository;

import com.nurullah.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByPostIdAndUserId(Optional<Integer> postId, Optional<Integer> userId);

    List<Comment> findByPostId(Optional<Integer> postId);

    List<Comment> findByUserId(Optional<Integer> userId);


    @Query(value = "select 'gönderinize yorum yaptı',comments.post_id,users.avatar,users.username from comments  left join users  on users.id=comments.user_id where comments.post_id in :postIds ",nativeQuery = true)
    List<Object> findUserTopComments(@Param("postIds") List<Integer>postIds);
}
