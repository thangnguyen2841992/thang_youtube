package com.thang.youtube.service.view;

import com.thang.youtube.model.entity.View;
import com.thang.youtube.repository.IViewRepository;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewService implements IViewService{
    @Autowired
    private IViewRepository viewRepository;
    @Override
    public Iterable<View> getAll() {
        return this.viewRepository.findAll();
    }

    @Override
    public Optional<View> getById(Long id) {
        return this.viewRepository.findById(id);
    }

    @Override
    public View save(View view) {
        return this.viewRepository.save(view);
    }

    @Override
    public void deleteById(Long id) {
        this.viewRepository.deleteById(id);
    }

    @Override
    public List<View> findViewByVideo_Id(Long videoId) {
        return this.viewRepository.findViewByVideo_Id(videoId);
    }

//    @Override
//    public String getWebBrowse() {
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//
//        String browserName = cap.getBrowserName();
//        String browserVersion = (String)cap.getCapability("browserVersion");
//        String osName = Platform.fromString((String)cap.getCapability("platformName")).name().toLowerCase();
//
//        return browserName + browserVersion + "-" + osName;
//    }
}
