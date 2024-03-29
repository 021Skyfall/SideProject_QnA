package com.study.reply.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.board.service.BoardService;
import com.study.exception.BusinessLogicException;
import com.study.exception.ExceptionCode;
import com.study.member.entity.Member;
import com.study.member.service.MemberService;
import com.study.reply.entity.Reply;
import com.study.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReplyService {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public void createReply(Reply reply) {

        Board findBoard = checkBoard(reply);
        replyRepository.save(reply);

        findBoard.setBoardStatus(Board.BoardStatus.QUESTION_ANSWERED);
        boardRepository.save(findBoard);
    }

    public void updateReply(Reply reply) {

        Reply findReply = verifyReply(reply.getReplyId());

        checkBoard(findReply);

        Optional.ofNullable(reply.getMessage())
                .ifPresent(findReply::setMessage);

        replyRepository.save(findReply);
    }

    public void deleteReply(long replyId) {

        Reply findReply = verifyReply(replyId);

        // 양방향 연관관계 매핑을 끊어서 삭제하기 위함
        Board findBoard = checkBoard(findReply);
        findBoard.setReply(null);

        replyRepository.delete(findReply);
    }

    private Reply verifyReply(Long replyId) {
        Optional<Reply> findReply = replyRepository.findById(replyId);
        return findReply.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));
    }

    private Board checkBoard(Reply reply) {
        Board findboard = boardService.findVerifiedBoard(reply.getBoard().getBoardId());
        if (findboard.getBoardStatus().getStepNumber() == 3) {
            throw new BusinessLogicException(ExceptionCode.DELETED_BOARD);
        }
        return findboard;
    }
}
