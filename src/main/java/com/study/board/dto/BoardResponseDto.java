package com.study.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
}
