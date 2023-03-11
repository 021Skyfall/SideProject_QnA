package com.study.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 저장 될때 등록
    private long boardId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
