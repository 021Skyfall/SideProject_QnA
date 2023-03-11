package com.study.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseDto {
    private String boardId;

    private String email;

    private String content;
}
