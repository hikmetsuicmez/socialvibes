package com.thedxs.socialvibes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeResponse {

    private Long id;
    private Long userId;
    private Long postId;

}
