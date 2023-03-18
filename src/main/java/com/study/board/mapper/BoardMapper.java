package com.study.board.mapper;

import com.study.board.dto.*;
import com.study.board.entity.Board;
import com.study.member.entity.Member;
import com.study.reply.entity.Reply;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {

    //    @Mapping(source = "member.memberId", target = "memberId") // 자동 매핑
    default Board BoardPostDtoToBoard(BoardPostDto boardPostDto) { // 수동 매핑
        // 매핑해야할 소스 필드
        Long memberId = boardPostDto.getMemberId();
        String password = boardPostDto.getPassword();
        String title = boardPostDto.getTitle();
        String content = boardPostDto.getContent();
        Integer acceess = boardPostDto.getAccessLevel();

        // 매핑 당할 타겟 필드
        Board board = new Board();
        board.setMember(new Member());
        board.getMember().setMemberId(memberId);
        board.setPassword(password);
        board.setTitle(title);
        board.setContent(content);
        board.setAccessLevel(acceess);

        return board;
    }

    //    @Mapping(source = "member.memberId", target = "memberID")
    default Board boardPatchDtoToBoard(BoardPatchDto boardPatchDto) {

        // 매핑해야할 소스 필드
        Long memberId = boardPatchDto.getMemberId();
        Long boardId = boardPatchDto.getBoardId();
        String password = boardPatchDto.getPassword();
        String title = boardPatchDto.getTitle();
        String contents = boardPatchDto.getContent();
        Integer accessLevel = boardPatchDto.getAccessLevel();

        // 매핑을 당할 타겟 필드
        Board board = new Board();
        board.setMember(new Member());
        board.getMember().setMemberId(memberId);
        board.setBoardId(boardId);
        board.setPassword(password);
        board.setTitle(title);
        board.setAccessLevel(accessLevel);
        board.setContent(contents);

        return board;
    }

    default Board boardGetDtoToBoard(BoardGetDto boardGetDto) {
        Long boardId = boardGetDto.getBoardId();
        Long memberId = boardGetDto.getMemberId();
        String password = boardGetDto.getPassword();

        Board board = new Board();
        board.setMember(new Member());
        board.getMember().setMemberId(memberId);
        board.setBoardId(boardId);
        board.setPassword(password);

        return board;
    }

    default Board boardDeleteDtoToBoard(BoardDeleteDto boardDeleteDto) {

        Long boardId = boardDeleteDto.getBoardId();
        Long memberId = boardDeleteDto.getMemberId();
        String password = boardDeleteDto.getPassword();

        Board board = new Board();
        board.setMember(new Member());
        board.getMember().setMemberId(memberId);
        board.setBoardId(boardId);
        board.setPassword(password);

        return board;
    }

    default BoardResponseDto BoardToResponseDto(Board board) {
        Long boardId = board.getBoardId();
        String title = board.getTitle();
        String content = board.getContent();
        Board.BoardAccessStatus boardAccessStatus = board.getBoardAccessStatus();
        Reply reply = board.getReply();
        
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBoardId(boardId);
        boardResponseDto.setTitle(title);
        boardResponseDto.setContent(content);
        boardResponseDto.setBoardAccessStatus(boardAccessStatus);
        boardResponseDto.setReply(new Reply());

        return boardResponseDto;
    }
}
