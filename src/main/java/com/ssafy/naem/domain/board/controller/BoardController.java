package com.ssafy.naem.domain.board.controller;

import com.ssafy.naem.config.BaseException;
import com.ssafy.naem.config.BaseResponse;
import com.ssafy.naem.config.BaseResponseStatus;
import com.ssafy.naem.domain.board.dto.request.BoardCreateRequest;
import com.ssafy.naem.domain.board.dto.response.BoardsResponse;
import com.ssafy.naem.domain.board.dto.response.BoardCreateResponse;
import com.ssafy.naem.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping("/new")
    public BaseResponse<BoardCreateResponse> createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        try {
            BoardCreateResponse createBoardResponse = boardService.createBoard(boardCreateRequest);
            return new BaseResponse<>(createBoardResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
//        boardService.createBoard(boardCreateRequest);
    }

    @ResponseBody
    @GetMapping("/")
    public BaseResponse<BoardsResponse> getAllBoards() {
        try {
            return new BaseResponse<>(boardService.getAllBoards());
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/active")
    public BaseResponse<BoardsResponse> getAllActiveBoards() {
        try {
            return new BaseResponse<>(boardService.getAllActiveBoards());
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/hide/{id}")
    public BaseResponse<Object> hideBoard(@PathVariable("id") Long id) {
        try {
            boardService.hideBoard(id);

            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public BaseResponse<Object> deleteBoard(@PathVariable("id") Long id) {
        try {
            boardService.deleteBoard(id);

            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
