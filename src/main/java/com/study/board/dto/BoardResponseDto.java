package com.study.board.dto;

import com.study.board.entity.Board;
import com.study.reply.dto.ReplyResponseDto;
import com.study.reply.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private Board.BoardAccessStatus boardAccessStatus;

    List<ReplyResponseDto> replyResponseDto;
}
