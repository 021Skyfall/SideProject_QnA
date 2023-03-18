package com.study.board.controller;

import com.study.board.dto.*;
import com.study.board.entity.Board;
import com.study.board.mapper.BoardMapper;
import com.study.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/boards")
@Validated
@AllArgsConstructor
public class BoardController {
    private final BoardMapper mapper;
    private final BoardService service;

    @PostMapping
    public ResponseEntity postBoard(@Validated @RequestBody BoardPostDto boardPostDto) {

        Board board = mapper.BoardPostDtoToBoard(boardPostDto);
        Board response = service.createBoard(board);

        return new ResponseEntity<>(mapper.BoardToResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @Validated @RequestBody BoardPatchDto boardPatchDto) {
        boardPatchDto.setBoardId(boardId);

        Board board = mapper.boardPatchDtoToBoard(boardPatchDto);
        Board response = service.updateBoard(board);

        return new ResponseEntity<>(mapper.BoardToResponseDto(response),HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long boardId,
                                   @Validated @RequestBody BoardGetDto boardGetDto) {

        boardGetDto.setBoardId(boardId);
        Board board = mapper.boardGetDtoToBoard(boardGetDto);
        Board response = service.getBoard(board);

        return new ResponseEntity<>(mapper.BoardToResponseDto(response),HttpStatus.OK);
    }

    @DeleteMapping("/{reply-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") @Positive long boardId,
                                      @Validated @RequestBody BoardDeleteDto boardDeleteDto) {
        boardDeleteDto.setBoardId(boardId);
        service.deleteBoard(mapper.boardDeleteDtoToBoard(boardDeleteDto));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}