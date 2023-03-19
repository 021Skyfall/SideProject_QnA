package com.study.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReplyPatchDto {
    // 로그인
    private Long memberId;
    private String password;

    @Setter
    private Long replyId;

    private String message;
}
