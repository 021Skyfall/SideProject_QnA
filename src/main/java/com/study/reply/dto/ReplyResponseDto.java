package com.study.reply.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyResponseDto {
    private String message;
    private LocalDateTime localDateTime;
}
