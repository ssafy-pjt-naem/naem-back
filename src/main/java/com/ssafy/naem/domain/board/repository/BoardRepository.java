package com.ssafy.naem.domain.board.repository;

import com.ssafy.naem.domain.board.entity.Board;
import com.ssafy.naem.domain.board.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByStatus(Status status);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Board b SET b.status = 'STATUS_HIDDEN', b.updated = CURRENT_TIMESTAMP WHERE b.id = :id")
//    int hideBoard(@Param("id") Long id);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Board b SET b.status = 'STATUS_DELETED', b.updated = CURRENT_TIMESTAMP WHERE b.id = :id")
//    int deleteBoard(@Param("id") Long id);
}
