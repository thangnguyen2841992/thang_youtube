package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional =  this.userService.getById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Tài khoản không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
}
