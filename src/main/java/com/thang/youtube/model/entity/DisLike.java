package com.thang.youtube.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "dislikes")
public class DisLike {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Video video;

    @ManyToOne
    private User user;
}
