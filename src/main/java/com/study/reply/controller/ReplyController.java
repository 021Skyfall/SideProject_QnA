package com.study.reply.controller;

import com.study.reply.dto.ReplyPatchDto;
import com.study.reply.dto.ReplyPostDto;
import com.study.reply.entity.Reply;
import com.study.reply.mapper.ReplyMapper;
import com.study.reply.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/reply")
@Validated
@AllArgsConstructor
public class ReplyController {
    private final ReplyService service;
    private final ReplyMapper replyMapper;

    @PostMapping
    public ResponseEntity postReply(@Validated @RequestBody ReplyPostDto replyPostDto) {
        Reply reply = replyMapper.replyPostDtoToReply(replyPostDto);
        service.createReply(reply);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{reply-id}")
    public ResponseEntity patchReply(@PathVariable("reply-id") @Positive Long replyId,
                                     @Validated @RequestBody ReplyPatchDto replyPatchDto) {
        replyPatchDto.setReplyId(replyId);

        service.updateReply(replyMapper.replyPatchDtoToReply(replyPatchDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{reply-id}")
    public ResponseEntity deleteReply(@PathVariable("reply-id") @Positive Long replyId) {

        service.deleteReply(replyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
