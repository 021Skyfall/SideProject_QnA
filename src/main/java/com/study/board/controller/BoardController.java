package com.study.board.controller;

import com.study.board.dto.BoardPostDto;
import com.study.board.entity.Board;
import com.study.board.mapper.BoardMapper;
import com.study.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@Validated
@AllArgsConstructor
public class BoardController {
    private final BoardMapper mapper;
    private final BoardService service;

    @PostMapping
    public ResponseEntity postBoard(@Validated @RequestBody BoardPostDto boardPostDto) {

        Board board = mapper.BoardPostDtoToMessage(boardPostDto);
        Board response = service.createboard(board);


        return new ResponseEntity<>(mapper.BoardToResponseDto(response), HttpStatus.CREATED);
    }
}
