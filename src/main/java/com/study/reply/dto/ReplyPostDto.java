package com.study.reply.dto;

import lombok.Getter;

@Getter
public class ReplyPostDto {
    // 답변 달아줄 게시글
    private Long boardId;

    private String message;
}
