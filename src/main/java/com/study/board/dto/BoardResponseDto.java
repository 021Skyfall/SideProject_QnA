package com.study.board.dto;

import com.study.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private Board.BoardAccessStatus boardAccessStatus;
}
