package com.study.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReplyPatchDto {
    @Setter
    private Long replyId;

    private String message;
}
