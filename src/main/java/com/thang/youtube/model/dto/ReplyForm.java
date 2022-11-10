package com.thang.youtube.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyForm {

    private String content;

    private Long commentId;

    private Long userId;
}
