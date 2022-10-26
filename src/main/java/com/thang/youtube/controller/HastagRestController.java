package com.thang.youtube.controller;

import com.thang.youtube.service.hastag.IHastagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/hastags")
public class HastagRestController {
    @Autowired
    private IHastagService hastagService;
}
