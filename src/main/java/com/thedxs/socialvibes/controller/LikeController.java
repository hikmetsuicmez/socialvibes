package com.thedxs.socialvibes.controller;


import com.thedxs.socialvibes.dto.LikeRequest;
import com.thedxs.socialvibes.dto.LikeResponse;
import com.thedxs.socialvibes.model.Like;
import com.thedxs.socialvibes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<List<LikeResponse>> retrieveAllLikes() {
        List<LikeResponse> list = likeService.getAllLikes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeResponse> getLike(@PathVariable Long id) {
        LikeResponse likeResponse = likeService.getLike(id);
        return ResponseEntity.ok(likeResponse);
    }

    @PostMapping
    public ResponseEntity<LikeResponse> createLike(@RequestBody LikeRequest request) {
        LikeResponse newLike = likeService.createLike(request);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.ok("Deleted: " + id);
    }
}
