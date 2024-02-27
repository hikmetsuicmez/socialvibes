package com.thedxs.socialvibes.controller;

import com.thedxs.socialvibes.dto.PostDto;
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
    public ResponseEntity<List<PostDto>> retrieveAllPosts() {
        List<PostDto> list = postService.getAllPosts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> retrievePost(@PathVariable Long id) {
        PostDto postDto = postService.getPost(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto newPost = postService.addPost(postDto);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
