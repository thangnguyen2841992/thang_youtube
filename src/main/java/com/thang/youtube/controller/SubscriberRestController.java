package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.service.subscriber.ISubscriberService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/subscribers")
public class SubscriberRestController {
    @Autowired
    private ISubscriberService subscriberService;
    @Autowired
    private IUserService userService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> showAllStudioSubscribe(@PathVariable Long memberId) {
        List<Subscriber> subscribers = this.subscriberService.findSubscribersByMember_Id(memberId);
        return new ResponseEntity<>(subscribers, HttpStatus.OK);
    }

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
    @DeleteMapping("/unSubscribe/user/{userId}/member/{memberId}")
    public ResponseEntity<?> unSubscribe(@PathVariable Long userId, @PathVariable Long memberId) {
        Optional<User> userOptional = this.userService.getById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Kênh không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> memberOptional = this.userService.getById(memberId);
        if (!memberOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Subscriber>subscriberOptional = this.subscriberService.findSubscribersByUser_IdAndMember_Id(userId, memberId);
        if (subscriberOptional.isPresent()) {
            this.subscriberService.deleteById(subscriberOptional.get().getId());
        }else {
            return new ResponseEntity<>(new Message("Người dùng chưa đăng ký kênh!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(subscriberOptional.get(), HttpStatus.OK);
    }
}
