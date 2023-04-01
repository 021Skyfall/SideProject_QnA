package com.study.reply.mapper;

import com.study.board.entity.Board;
import com.study.reply.dto.ReplyPatchDto;
import com.study.reply.dto.ReplyPostDto;
import com.study.reply.dto.ReplyResponseDto;
import com.study.reply.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {

    default Reply replyPostDtoToReply(ReplyPostDto replyPostDto) {
        Reply reply = new Reply();
        reply.setBoard(new Board());
        reply.getBoard().setBoardId(replyPostDto.getBoardId());
        reply.setMessage(replyPostDto.getMessage());

        return reply;
    }

    Reply replyPatchDtoToReply(ReplyPatchDto replyPatchDto);

    ReplyResponseDto replyToReplyResponseDto(Reply reply);

}
