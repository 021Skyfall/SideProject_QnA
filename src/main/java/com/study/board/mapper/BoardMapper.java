package com.study.board.mapper;

import com.study.board.dto.BoardPostDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import com.study.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {

//    @Mapping(source = "member.memberId", target = "memberId") // 자동 매핑
    default Board BoardPostDtoToBoard(BoardPostDto boardPostDto) { // 수동 매핑
        // 매핑해야할 소스 필드
        Long memberId = boardPostDto.getMemberId();
        String password = boardPostDto.getPassword();
        String title = boardPostDto.getTitle();
        String content = boardPostDto.getContent();

        // 매핑 당할 타겟 필드
        Board board = new Board();
        board.setMember(new Member());
        board.getMember().setMemberId(memberId);
        board.setPassword(password);
        board.setTitle(title);
        board.setContent(content);

        return board;
    }
    default BoardResponseDto BoardToResponseDto(Board board){
        // 매핑해야할 소스 필드
        Long boardId = board.getBoardId();
        String title = board.getTitle();
        String content = board.getContent();

        // 매핑 당할 타겟 필드
        BoardResponseDto boardResponseDto = BoardResponseDto
                .builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .build();

        return boardResponseDto;
    }
}
