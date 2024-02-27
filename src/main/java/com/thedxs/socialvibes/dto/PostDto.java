package com.thedxs.socialvibes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Long userId;

}
