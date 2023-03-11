package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardService {
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    public Board createboard(Board board){
        memberService.verifyEmailAndPassword(board.getEmail(), board.getPassword());

        return boardRepository.save(board);
    }
}
