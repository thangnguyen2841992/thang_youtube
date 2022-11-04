package com.thang.youtube.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentForm {

    private String content;

    private Long videoId;

    private Long userId;
}
