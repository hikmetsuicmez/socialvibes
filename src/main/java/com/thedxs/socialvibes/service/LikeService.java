package com.thedxs.socialvibes.service;

import com.thedxs.socialvibes.dto.LikeDtoConverter;
import com.thedxs.socialvibes.dto.LikeRequest;
import com.thedxs.socialvibes.dto.LikeResponse;
import com.thedxs.socialvibes.model.Like;
import com.thedxs.socialvibes.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeDtoConverter dtoConverter;

    public List<LikeResponse> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(dtoConverter::convertToLikeDto)
                .collect(Collectors.toList());
    }

    public LikeResponse getLike(Long id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like Not Found!"));
        return dtoConverter.convertToLikeDto(like);
    }

    public LikeResponse createLike(LikeRequest request) {
        Like like = dtoConverter.convertToLike(request);
        Like dbLike = likeRepository.save(like);
        return dtoConverter.convertToLikeDto(dbLike);
    }

    public void deleteLike(Long id) {
        if (id == null) {
            throw new RuntimeException("Id not found");
        }
        likeRepository.deleteById(id);
    }
}
