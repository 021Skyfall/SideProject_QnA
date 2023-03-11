package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.exception.BusinessLogicException;
import com.study.exception.ExceptionCode;
import com.study.member.entity.Member;
import com.study.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardService {
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    public Board createboard(Board board){
        Member findMember = memberService.findVerifiedMember(board.getMember().getMemberId());

        if (!board.getPassword().equals(findMember.getPassword()))
            throw new BusinessLogicException(ExceptionCode.DOES_NOT_MATCHED_PASSWORD);

        return boardRepository.save(board);
    }
}