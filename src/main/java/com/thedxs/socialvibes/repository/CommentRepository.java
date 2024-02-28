package com.thedxs.socialvibes.repository;

import com.thedxs.socialvibes.dto.CommentResponse;
import com.thedxs.socialvibes.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<CommentResponse> findByUserIdAndPostId(Long userId, Long postId);
    List<CommentResponse> findByUserId(Long userId);
    List<CommentResponse> findByPostId(Long postId);


}
