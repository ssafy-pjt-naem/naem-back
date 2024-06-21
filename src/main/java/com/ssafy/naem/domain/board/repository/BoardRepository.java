package com.ssafy.naem.domain.board.repository;

import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndStatus(Long id, Status status);

    Optional<Board> findByIdAndStatusNot(Long id, Status status);

    List<Board> findAllByStatus(Status status);

    Optional<Board> findByNameAndStatusNot(String name, Status status);

}
