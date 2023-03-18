package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.exception.BusinessLogicException;
import com.study.exception.ExceptionCode;
import com.study.member.entity.Member;
import com.study.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    public Board createBoard(Board board){
        logIn(board);

        if (board.getAccessLevel() == 1)
            board.setBoardAccessStatus(Board.BoardAccessStatus.PUBLIC);
        else board.setBoardAccessStatus(Board.BoardAccessStatus.SECRET);

        return boardRepository.save(board);
    }

    public Board updateBoard(Board board) {

        logIn(board);

        Board findBoard = verifiedIdAndPassword(board);

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

    public Board getBoard(Board board) {

        logIn(board);

        Board findBoard = findVerifiedBoard(board.getBoardId());

        if (findBoard.getBoardAccessStatus().getStepNumber() == 2) {
            verifiedIdAndPassword(board);
        }

        alreadyDeletedBoard(findBoard);



        return findBoard;
    }

    public void deleteBoard(Board board) {

        logIn(board);

        Board findBoard = verifiedIdAndPassword(board);

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

    private void logIn(Board board) {
        Member member = memberService.findVerifiedMember(board.getMember().getMemberId());
        if (!board.getPassword().equals(member.getPassword()))
            throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCHED);
    }

    private Board verifiedIdAndPassword(Board board) {
        Board findBoard = findVerifiedBoard(board.getBoardId());

        if (board.getMember().getMemberId() != 1) {

            if (!Objects.equals(board.getMember().getMemberId(), findBoard.getMember().getMemberId()))
                throw new BusinessLogicException(ExceptionCode.ID_MISMATCHED);

            if (!Objects.equals(board.getPassword(), findBoard.getPassword()))
                throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCHED);

        }

        return findBoard;
    }
}