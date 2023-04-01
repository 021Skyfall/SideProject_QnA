package com.study.board.dto;

import com.study.board.entity.Board;
import com.study.reply.dto.ReplyResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardResponseDto {
    private Long memberId;
    private Long boardId;
    private String title;
    private String content;
    private Board.BoardAccessStatus boardAccessStatus;

    List<ReplyResponseDto> replyResponseDto;
}
