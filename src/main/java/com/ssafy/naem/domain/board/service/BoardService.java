package com.ssafy.naem.domain.board.service;

import com.ssafy.naem.config.BaseException;
import com.ssafy.naem.config.BaseResponseStatus;
import com.ssafy.naem.domain.board.dto.request.BoardChangeNameRequest;
import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.dto.response.BoardsResponse;
import com.ssafy.naem.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.naem.domain.board.dto.response.BoardResponse;
import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.entity.Status;
import com.ssafy.naem.domain.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardsResponse getAllActiveBoards() throws BaseException {
        try {
            List<Board> boardListResult = boardRepository.findAllByStatus(Status.STATUS_ACTIVE);
            List<BoardResponse> boardInfos = boardListResult.stream()
                    .map(BoardResponse::from)
                    .toList();

            return new BoardsResponse(boardInfos);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public BoardsResponse getAllBoards() throws BaseException {
        try {
            List<Board> boardListResult = boardRepository.findAll();
            List<BoardResponse> boardInfos = boardListResult.stream()
                    .map(BoardResponse::from)
                    .toList();

            return new BoardsResponse(boardInfos);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) throws BaseException {
        Board newBoard = Board.builder()
                .name(boardCreateRequest.name())
                .build();

        Optional<Board> board = boardRepository.findByNameAndStatusNot(newBoard.getName(), Status.STATUS_DELETED);
        if (board.isPresent()) {
            throw new BaseException(BaseResponseStatus.BOARD_ALREADY_EXISTS);
        }

        try {
            Board result = boardRepository.save(newBoard);
//            System.out.println(result.toString());

            return new BoardCreateResponse(result.getId());
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

    @Transactional
    public void changeBoardName(Long id, BoardChangeNameRequest boardChangeNameRequest) throws BaseException {
        String newName = boardChangeNameRequest.name();

        Optional<Board> board = boardRepository.findByNameAndStatusNot(newName, Status.STATUS_DELETED);
        if (board.isPresent()) {
            throw new BaseException(BaseResponseStatus.BOARD_ALREADY_EXISTS);
        }

        board = boardRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED);
        if (board.isEmpty()) {
            throw new BaseException(BaseResponseStatus.BOARD_NOT_FOUND);
        }

        Board foundBoard = board.get();

        foundBoard.changeName(newName);
    }

    @Transactional
    public void hideBoard(Long id) throws BaseException {

        Optional<Board> board = boardRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED);
        if (board.isEmpty()) {
            throw new BaseException(BaseResponseStatus.BOARD_NOT_FOUND);
        }

        Board foundBoard = board.get();

        foundBoard.updateStatusToHidden();
    }

    @Transactional
    public void deleteBoard(Long id) throws BaseException {

        Optional<Board> board = boardRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED);
        if (board.isEmpty()) {
            throw new BaseException(BaseResponseStatus.BOARD_NOT_FOUND);
        }

        Board foundBoard = board.get();

        foundBoard.updateStatusToDeleted();
    }
}
