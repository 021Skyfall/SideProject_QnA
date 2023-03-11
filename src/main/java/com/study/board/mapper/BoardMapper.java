package com.study.board.mapper;

import com.study.board.dto.BoardPostDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board BoardPostDtoToMessage(BoardPostDto BoardPostDto);
    BoardResponseDto BoardToResponseDto(Board Board);
}
