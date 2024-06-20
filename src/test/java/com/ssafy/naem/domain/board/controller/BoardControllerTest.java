package com.ssafy.naem.domain.board.controller;

import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(BoardController.class).build();
//    }

    @Test
    void createBoard() throws Exception {
        Board newBoard = Board.builder().name("test1").build();
        Long id = newBoard.getId();
        String boardName = newBoard.getName();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/boards")
                .content("{\"name\": \"" + boardName + "\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result.id")
                        .isNumber());
    }

    @Test
    void getAllBoards() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result.boardList")
                        .isArray());
    }

    @Test
    void getAllActiveBoards() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/boards/active")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result.boardList")
                        .isArray());
    }

    @Test
    void hideBoard() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/boards/17/hide")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteBoard() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/boards/16/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}