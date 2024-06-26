package com.ssafy.naem.domain.board.service;

import com.ssafy.naem.global.config.BaseException;
import com.ssafy.naem.global.config.BaseResponseStatus;
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

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardsResponse getAllActiveBoards() {

        List<Board> boardListResult = boardRepository.findAllByStatus(Status.STATUS_ACTIVE);
        List<BoardResponse> boardInfos = boardListResult.stream()
                .map(BoardResponse::from)
                .toList();

        return new BoardsResponse(boardInfos);
    }

    public BoardsResponse getAllBoards() {

        List<Board> boardListResult = boardRepository.findAll();
        List<BoardResponse> boardInfos = boardListResult.stream()
                .map(BoardResponse::from)
                .toList();

        return new BoardsResponse(boardInfos);
    }

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) {
        Board newBoard = Board.builder()
                .name(boardCreateRequest.name())
                .build();

        boardRepository.findByNameAndStatusNot(newBoard.getName(), Status.STATUS_DELETED)
                .ifPresent(foundBoard -> {
                    throw new BaseException(BaseResponseStatus.BOARD_ALREADY_EXISTS);
                });

        Board result = boardRepository.save(newBoard);

        return new BoardCreateResponse(result.getId());
    }

    @Transactional
    public void changeBoardName(Long id, BoardChangeNameRequest boardChangeNameRequest) {

        String newName = boardChangeNameRequest.name();

        Board foundBoard = boardRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BOARD_NOT_FOUND));

        boardRepository.findByNameAndStatusNot(newName, Status.STATUS_DELETED)
                .ifPresent(duplicateBoard -> {
                    throw new BaseException(BaseResponseStatus.BOARD_ALREADY_EXISTS);
                });

        foundBoard.changeName(newName);
    }

    @Transactional
    public void hideBoard(Long id) {

        Board foundBoard = boardRepository.findByIdAndStatus(id, Status.STATUS_ACTIVE)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BOARD_NOT_FOUND));

        foundBoard.updateStatusToHidden();
    }

    @Transactional
    public void deleteBoard(Long id) {

        Board foundBoard = boardRepository.findByIdAndStatusNot(id, Status.STATUS_DELETED)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BOARD_NOT_FOUND));

        foundBoard.updateStatusToDeleted();
    }
}
