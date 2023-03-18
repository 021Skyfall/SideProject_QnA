package com.study.board.dto;

import com.study.board.entity.Board;
import com.study.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private Board.BoardAccessStatus boardAccessStatus;
    private Reply reply;
}
