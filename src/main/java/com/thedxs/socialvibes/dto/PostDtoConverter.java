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

    public Post convertToPost(PostRequest request) {
        return Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .user(repository.findById(request.getUserId()).orElseThrow())
                .build();
    }

    public PostResponse convertToPostDto(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .userId(post.getUser().getId())
                .build();
    }

}
