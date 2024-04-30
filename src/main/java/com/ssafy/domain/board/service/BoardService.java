package com.ssafy.domain.board.service;

import com.ssafy.domain.board.dto.BoardRequest;
import com.ssafy.domain.board.dto.BoardResponse;
import com.ssafy.domain.board.model.Board;
import com.ssafy.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    static private final String successMsg = "게시글 저장에 성공했습니다.";
    static private final String failMsg = "게시글 작성에 실패했습니다.";

    private final BoardRepository boardRepository;

    public BoardResponse createBoard(BoardRequest boardRequest) {
        try {
            boardRepository.save(boardRequest.toEntity());
            return BoardResponse
                    .builder()
                    .msg(successMsg)
                    .build();
        }
        catch(Exception e){
            e.printStackTrace();
            return BoardResponse
                    .builder()
                    .msg(failMsg)
                    .build();

        }
    }
}
