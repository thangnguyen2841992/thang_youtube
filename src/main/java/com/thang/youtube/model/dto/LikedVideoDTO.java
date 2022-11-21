package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikedVideoDTO {

    private Long id;

    private String likedVideoTime;


    private VideoResponse video;

    private User user;
}
