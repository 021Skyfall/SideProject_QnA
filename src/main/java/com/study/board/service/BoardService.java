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
        Member member = memberService.findVerifiedMember(board.getMember().getMemberId());
        if (!board.getPassword().equals(member.getPassword()))
            throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCHED);

        return boardRepository.save(board);
    }
}