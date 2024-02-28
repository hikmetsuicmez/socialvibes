package com.thedxs.socialvibes.dto;

import com.thedxs.socialvibes.model.Comment;
import com.thedxs.socialvibes.repository.PostRepository;
import com.thedxs.socialvibes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment convertToComment(CommentRequest request) {
        return Comment.builder()
                .content(request.getContent())
                .user(userRepository.findById(request.getUserId()).orElseThrow())
                .post(postRepository.findById(request.getPostId()).orElseThrow())
                .build();
    }

    public CommentResponse convertToCommentDto(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .build();
    }
}
