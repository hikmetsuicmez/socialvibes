package com.thedxs.socialvibes.controller;

import com.thedxs.socialvibes.dto.PostRequest;
import com.thedxs.socialvibes.dto.PostResponse;
import com.thedxs.socialvibes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> retrieveAllPosts() {
        List<PostResponse> list = postService.getAllPosts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> retrievePost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
        PostResponse newPost = postService.addPost(request);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        PostResponse updatedPost = postService.updatePost(id,request);
        return ResponseEntity.ok(updatedPost);
    }

}
