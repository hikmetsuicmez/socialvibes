package com.thedxs.socialvibes.service;

import com.thedxs.socialvibes.dto.PostDtoConverter;
import com.thedxs.socialvibes.model.Post;
import com.thedxs.socialvibes.repository.PostRepository;
import com.thedxs.socialvibes.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter dtoConverter;

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(dtoConverter::convertToPostDto)
                .collect(Collectors.toList());
    }


    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
        return dtoConverter.convertToPostDto(post);
    }


    public PostDto addPost(PostDto postDto) {
        Post post = dtoConverter.convertToPost(postDto);
        Post dbPost = postRepository.save(post);
        return dtoConverter.convertToPostDto(dbPost);
    }
}
