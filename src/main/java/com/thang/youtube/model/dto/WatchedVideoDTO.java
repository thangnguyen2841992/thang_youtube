package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedVideoDTO {

    private Long id;

    private String watchedTime;


    private VideoResponse video;

    private User user;

}
