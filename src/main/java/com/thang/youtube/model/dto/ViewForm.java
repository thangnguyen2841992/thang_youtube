package com.thang.youtube.model.dto;


import com.thang.youtube.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewForm {
    private Long videoId;

    private Long currentUserID;

}
