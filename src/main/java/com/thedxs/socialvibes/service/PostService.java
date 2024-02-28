package com.thedxs.socialvibes.service;

import com.thedxs.socialvibes.dto.PostDtoConverter;
import com.thedxs.socialvibes.dto.PostRequest;
import com.thedxs.socialvibes.model.Post;
import com.thedxs.socialvibes.repository.PostRepository;
import com.thedxs.socialvibes.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter dtoConverter;

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(dtoConverter::convertToPostDto)
                .collect(Collectors.toList());
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
        return dtoConverter.convertToPostDto(post);
    }

    public PostResponse addPost(PostRequest postRequest) {
        Post post = dtoConverter.convertToPost(postRequest);
        Post dbPost = postRepository.save(post);
        return dtoConverter.convertToPostDto(dbPost);
    }

    public PostResponse updatePost(Long id, PostRequest request) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found witd id: " + id));
        existingPost.setTitle(request.getTitle());
        existingPost.setContent(request.getContent());
        Post updatedPost = postRepository.save(existingPost);
        return dtoConverter.convertToPostDto(updatedPost);
    }
}
