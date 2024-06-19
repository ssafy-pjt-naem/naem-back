package com.ssafy.naem.domain.board.service;

import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
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

    public List<Board> getAllActiveBoards() {
        List<Board> boardListResult = boardRepository.findAllByStatus(Status.STATUS_ACTIVE);

        return boardListResult;
    }

    public List<Board> getAllBoards() {
        List<Board> boardListResult = boardRepository.findAll();

        return boardListResult;
    }

    @Transactional
    public Board createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = Board.builder()
                .name(boardCreateRequest.name())
                .build();

        Board result = boardRepository.save(board);

        return result;
    }

    @Transactional
    public void hideBoard(Long id) {

        Optional<Board> foundBoard = boardRepository.findById(id);
        if (foundBoard.isPresent()) {
            Board board = foundBoard.get();
            board.updateStatusToHidden();
        }
    }

    @Transactional
    public void deleteBoard(Long id) {

        Optional<Board> foundBoard = boardRepository.findById(id);
        if (foundBoard.isPresent()) {
            Board board = foundBoard.get();
            board.updateStatusToDeleted();
        }
    }
}
