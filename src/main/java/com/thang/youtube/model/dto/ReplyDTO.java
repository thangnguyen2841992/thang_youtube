package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long id;

    private String content;

    private String dateCreated;


    private Comment comment;

    private User user;
}
