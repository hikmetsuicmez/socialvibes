package com.thedxs.socialvibes.dto;

import com.thedxs.socialvibes.model.Post;
import com.thedxs.socialvibes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDtoConverter {

    private final ModelMapper modelMapper;
    private final UserRepository repository;


    public PostDto convertToPostDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }

    public Post convertToPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(repository.findById(postDto.getUserId()).orElseThrow());
        return post;
    }

}
