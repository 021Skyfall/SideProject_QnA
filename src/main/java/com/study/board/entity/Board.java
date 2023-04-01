package com.study.board.entity;

import com.study.audit.Auditable;
import com.study.member.entity.Member;
import com.study.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus = BoardStatus.QUESTION_REGISTRATION;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer accessLevel;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(mappedBy = "board", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Reply reply;

    @Enumerated(EnumType.STRING)
    private BoardAccessStatus boardAccessStatus;

    @AllArgsConstructor
    public enum BoardStatus {
        QUESTION_REGISTRATION(1, "질문 등록"),
        QUESTION_ANSWERED(2, "답변 완료"),
        QUESTION_DELETE(3, "질문 삭제");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;
    }

    @AllArgsConstructor
    public enum BoardAccessStatus {
        PUBLIC(1,"공개글"),
        SECRET(2,"비밀글");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;
    }
}
