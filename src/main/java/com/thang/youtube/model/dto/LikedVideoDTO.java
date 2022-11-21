package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikedVideoDTO {

    private Long id;

    private String likedTime;


    private Video video;

    private User user;
}
