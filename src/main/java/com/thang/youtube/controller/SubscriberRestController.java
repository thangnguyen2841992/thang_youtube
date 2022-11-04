package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.service.subscriber.ISubscriberService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subscribers")
public class SubscriberRestController {
    @Autowired
    private ISubscriberService subscriberService;
    @Autowired
    private IUserService userService;

    @PostMapping("/addMember/user/{userId}/member/{memberId}")
    public ResponseEntity<?> addNewMember(@PathVariable Long userId, @PathVariable Long memberId) {
        Subscriber subscriber = new Subscriber();
        if (!this.userService.getById(userId).isPresent()) {
            return new ResponseEntity<>(new Message("Tài khoản không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        if (!this.userService.getById(memberId).isPresent()) {
            return new ResponseEntity<>(new Message("Tài khoản không tồn tại"), HttpStatus.BAD_REQUEST);
        }
        subscriber.setUser(this.userService.getById(userId).get());
        subscriber.setMember(this.userService.getById(memberId).get());
        this.subscriberService.save(subscriber);
        return new ResponseEntity<>(subscriber, HttpStatus.OK);
    }

    @PostMapping("/checkMember/user/{userId}/member/{memberId}")
    public ResponseEntity<Check> checkMember(@PathVariable Long userId, @PathVariable Long memberId) {
        List<Subscriber> subscriberList = this.subscriberService.findSubscribersByUser_Id(userId);
        boolean checkMember = false;
        for (int i = 0; i < subscriberList.size(); i++) {
            if (subscriberList.get(i).getMember().getId() == memberId) {
                checkMember = true;
                break;
            }
        }
        Check check = new Check();
        check.setIsSubscriber(checkMember);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}
