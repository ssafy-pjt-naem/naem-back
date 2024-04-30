package com.ssafy.domain.board.dto;

import com.ssafy.domain.board.model.Board;
import com.ssafy.domain.board.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class BoardRequest {

    private String name;

    public Board toEntity(){
        return Board.builder()
                .name(name)
                .created(new Date())
                .status(Status.STATUS_ACTIVE)
                .build();
    }
}
