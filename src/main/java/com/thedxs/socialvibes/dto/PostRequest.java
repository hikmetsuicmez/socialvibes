package com.thedxs.socialvibes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequest {

    private String title;
    private String content;
    private Long userId;
}
