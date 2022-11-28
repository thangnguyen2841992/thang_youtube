package com.thang.youtube.service.view;

import com.thang.youtube.model.entity.View;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface IViewService extends IGeneralService<View> {
    List<View> findViewByVideo_Id(Long videoId);
//    String getWebBrowse();
}
