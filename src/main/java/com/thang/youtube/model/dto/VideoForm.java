package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoForm {
    private String name;

    private String url;

    private Long playListId;


    private Long hastagId;


    private Long userId;


}
