package com.thang.youtube.service.playList;

import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface IPlayListService extends IGeneralService<PlayList> {
    List<PlayList> findPlayListByUser_Id(Long userId);

}
