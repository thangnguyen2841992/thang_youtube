package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.service.hastag.IHastagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/hastags")
public class HastagRestController {
    @Autowired
    private IHastagService hastagService;

    @GetMapping
    public ResponseEntity<?> getAllHastag() {
        List<Hastag>  hastags = (List<Hastag>) this.hastagService.getAll();
        if (hastags.size() == 0) {
            return new ResponseEntity<>(new Message("Không có hastag nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hastags, HttpStatus.OK);
    }
}
