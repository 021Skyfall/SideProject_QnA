package com.study.reply.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ReplyDeleteDto {
    @Setter
    private Long replyId;
    // 로그인
    private Long memberId;
    private String password;
}
