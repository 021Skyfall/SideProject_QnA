package com.study.reply.mapper;

import com.study.reply.dto.ReplyDeleteDto;
import com.study.reply.dto.ReplyPatchDto;
import com.study.reply.dto.ReplyPostDto;
import com.study.reply.dto.ReplyResponseDto;
import com.study.reply.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyMapper {
    @Mapping(source = "memberId",target = "member.memberId")
    @Mapping(source = "boardId",target = "board.boardId")
    Reply replyPostDtoToReply(ReplyPostDto replyPostDto);

    @Mapping(source = "memberId",target = "member.memberId")
    Reply replyPatchDtoToReply(ReplyPatchDto replyPatchDto);

    @Mapping(source = "memberId",target = "member.memberId")
    Reply replyDeleteDtoToReply(ReplyDeleteDto replyDeleteDto);
}
