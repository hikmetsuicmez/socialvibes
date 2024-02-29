package com.thedxs.socialvibes.controller;

import com.thedxs.socialvibes.dto.CommentRequest;
import com.thedxs.socialvibes.dto.CommentResponse;
import com.thedxs.socialvibes.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> retrieveAllComments(@RequestParam Optional<Long> userId,
                                                                     @RequestParam Optional<Long> postId) {
        List<CommentResponse> list = commentService.getAllCommentsWithParam(userId,postId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {
        CommentResponse comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request) {
        CommentResponse newComment = commentService.createComment(request);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id, @RequestBody CommentRequest request) {
        CommentResponse updatedComment = commentService.updateComment(id,request);
        return ResponseEntity.ok(updatedComment);
    }


}
