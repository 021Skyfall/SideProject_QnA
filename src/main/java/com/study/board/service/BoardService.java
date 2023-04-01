package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.exception.BusinessLogicException;
import com.study.exception.ExceptionCode;
import com.study.member.entity.Member;
import com.study.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Board createBoard(Board board){
        verifyMember(board.getMember().getMemberId());

        if (board.getAccessLevel() == 1)
            board.setBoardAccessStatus(Board.BoardAccessStatus.PUBLIC);
        else board.setBoardAccessStatus(Board.BoardAccessStatus.SECRET);

        return boardRepository.save(board);
    }

    public Board updateBoard(Board board) {

        verifyMember(board.getMember().getMemberId());

        Board findBoard = findVerifiedBoard(board.getBoardId());

        if (findBoard.getBoardStatus().getStepNumber() > 1)
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_BOARD);

        Optional.ofNullable(board.getTitle())
                .ifPresent(findBoard::setTitle);
        Optional.ofNullable(board.getContent())
                .ifPresent(findBoard::setContent);
        Optional.ofNullable(board.getAccessLevel())
                .ifPresent(findBoard::setAccessLevel);

        if (findBoard.getAccessLevel() == 1)
           findBoard.setBoardAccessStatus(Board.BoardAccessStatus.PUBLIC);
        else findBoard.setBoardAccessStatus(Board.BoardAccessStatus.SECRET);

        return boardRepository.save(findBoard);
    }

    public Board getBoard(long boardId) {

        Board findBoard = findVerifiedBoard(boardId);

        alreadyDeletedBoard(findBoard);

        return findBoard;
    }

    public Page<Board> getBoards(int page, int size, int filter) {
        if (filter == 1) {
            return boardRepository.findAll(PageRequest.of(
                    page, size, Sort.by("boardId").descending()));
        } else {
            return boardRepository.findAll(PageRequest.of(
                    page, size, Sort.by("boardId").ascending()));
        }
    }

    public void deleteBoard(long boardId) {

        Board findBoard = findVerifiedBoard(boardId);

        alreadyDeletedBoard(findBoard);

        findBoard.setBoardStatus(Board.BoardStatus.QUESTION_DELETE);

        boardRepository.save(findBoard);
    }

    public Board findVerifiedBoard(long boardId) {
        Optional<Board> optionalBoard =
                boardRepository.findById(boardId);
        Board findBoard =
                optionalBoard.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
        return findBoard;
    }

    private static void alreadyDeletedBoard(Board board) {
        if (board.getBoardStatus().getStepNumber() == 3 )
            throw new BusinessLogicException(ExceptionCode.DELETED_BOARD);
    }

    public void verifyMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

    }
}