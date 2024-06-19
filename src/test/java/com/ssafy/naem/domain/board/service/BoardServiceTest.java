package com.ssafy.naem.domain.board.service;

import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.entity.Status;
import com.ssafy.naem.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void getAllActiveBoards() {
        // given
        List<Board> boards = List.of(
                Board.builder().name("test1").build(),
                Board.builder().name("test2").status(Status.STATUS_HIDDEN).build()
        );

        List<Board> activeBoards = List.of(
                Board.builder().name("test1").build()
        );

        when(boardRepository.findAllByStatus(Status.STATUS_ACTIVE)).thenReturn(activeBoards);

        // when
        List<Board> allActiveBoards = boardRepository.findAllByStatus(Status.STATUS_ACTIVE);

        // then
        assertNotNull(allActiveBoards);
        assertEquals(1, allActiveBoards.size());

        verify(boardRepository, times(1)).findAllByStatus(Status.STATUS_ACTIVE);

    }

    @Test
    void getAllBoards() {
        // given
        List<Board> boards = List.of(
                Board.builder().name("test1").build(),
                Board.builder().name("test2").build()
        );

        when(boardRepository.findAll()).thenReturn(boards);
        // when
        List<Board> allBoards = boardRepository.findAll();

        // then
        assertNotNull(allBoards);
        assertEquals(2, allBoards.size());

        verify(boardRepository, times(1)).findAll();
    }

    @Test
    void createBoard() {
        // given
        BoardCreateRequest boardCreateRequest = new BoardCreateRequest("test게시판");
        Board board = Board.builder()
                .name(boardCreateRequest.name())
                .build();

        when(boardRepository.save(any(Board.class))).thenReturn(board);

        // when
        Board createdBoard = boardService.createBoard(boardCreateRequest);
        System.out.println(createdBoard);

        // then
        assertNotNull(createdBoard);
        assertEquals("test게시판", createdBoard.getName());

        verify(boardRepository, times(1)).save(any(Board.class));
//        System.out.println(createdBoard);
    }

    @Test
    void hideBoard() {
        // given
        Board board = Board.builder()
                .id(1L)
                .name("test")
                .status(Status.STATUS_ACTIVE)
                .build();

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // when
        boardService.hideBoard(1L);

        // then
        assertThat(board.getStatus()).isEqualTo(Status.STATUS_HIDDEN);
//        System.out.println(board.getStatus());
    }

    @Test
    void deleteBoard() {
        // given
        Board board = Board.builder()
                .id(1L)
                .name("test")
                .status(Status.STATUS_ACTIVE)
                .build();

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // when
        boardService.deleteBoard(1L);

        // then
        assertThat(board.getStatus()).isEqualTo(Status.STATUS_DELETED);
    }
}