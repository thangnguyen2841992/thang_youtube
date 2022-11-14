package com.thang.youtube.model.dto;

import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
    private Long id;

    private String content;

    private String dateCreated;

    private Video video;

    private User user;

    private Integer totalLike;

    private Integer totalDislike;

    private Check checkLikeComment;

    private List<ReplyDTO> replyDTOList;

    private Integer totalReply;

    private List<Long> listUserLikeComment;

}
