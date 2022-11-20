package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikedVideoDTO {
    private Long id;

    private String likedVideoTime;


    private VideoResponse video;

    private User user;
}
