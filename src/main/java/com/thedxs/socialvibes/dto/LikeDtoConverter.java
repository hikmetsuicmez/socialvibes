package com.thedxs.socialvibes.dto;

import com.thedxs.socialvibes.model.Like;
import com.thedxs.socialvibes.repository.PostRepository;
import com.thedxs.socialvibes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeDtoConverter {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeResponse convertToLikeDto(Like like) {
        return LikeResponse.builder()
                .id(like.getId())
                .userId(like.getUser().getId())
                .postId(like.getPost().getId())
                .build();
    }

    public Like convertToLike(LikeRequest request) {
        return Like.builder()
                .user(userRepository.findById(request.getUserId()).orElseThrow())
                .post(postRepository.findById(request.getPostId()).orElseThrow())
                .build();
    }
}
