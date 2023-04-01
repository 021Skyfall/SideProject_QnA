package com.study.reply.entity;

import com.study.audit.Auditable;
import com.study.board.entity.Board;
import com.study.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column
    private String message;

    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
