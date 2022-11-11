package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.service.hastag.IHastagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/hastags")
public class HastagRestController {
    @Autowired
    private IHastagService hastagService;

    @GetMapping
    public ResponseEntity<?> getAllHastag() {
        List<Hastag> hastags = (List<Hastag>) this.hastagService.getAll();
        if (hastags.size() == 0) {
            return new ResponseEntity<>(new Message("Không có hastag nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hastags, HttpStatus.OK);
    }

    @GetMapping("/{hastagId}")
    public ResponseEntity<?> getHastagById(@PathVariable Long hastagId) {
        Optional<Hastag> hastagOptional = this.hastagService.getById(hastagId);
        if (!hastagOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Hastag không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(hastagOptional.get(), HttpStatus.OK);
    }
}
