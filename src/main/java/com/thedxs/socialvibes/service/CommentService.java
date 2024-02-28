package com.thedxs.socialvibes.service;

import com.thedxs.socialvibes.dto.CommentDtoConverter;
import com.thedxs.socialvibes.dto.CommentRequest;
import com.thedxs.socialvibes.dto.CommentResponse;
import com.thedxs.socialvibes.model.Comment;
import com.thedxs.socialvibes.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter dtoConverter;

    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            List<Comment> comments = commentRepository.findAll();
            return comments.stream().map(dtoConverter::convertToCommentDto)
                    .collect(Collectors.toList());
        }
    }

    public CommentResponse getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return dtoConverter.convertToCommentDto(comment);
    }

    public CommentResponse createComment(CommentRequest request) {
        Comment comment = dtoConverter.convertToComment(request);
        Comment dbComment = commentRepository.save(comment);
        return dtoConverter.convertToCommentDto(dbComment);
    }

    public CommentResponse updateComment(Long id, CommentRequest request) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        existingComment.setContent(request.getContent());
        Comment updatedComment = commentRepository.save(existingComment);
        return dtoConverter.convertToCommentDto(updatedComment);
    }
}
