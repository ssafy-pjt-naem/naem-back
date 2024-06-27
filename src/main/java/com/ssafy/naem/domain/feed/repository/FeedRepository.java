package com.ssafy.naem.domain.feed.repository;

import com.ssafy.naem.domain.feed.entity.Feed;
import com.ssafy.naem.domain.feed.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findById(Long id);

    Optional<Feed> findByIdAndStatus(Long id, Status status);

    Optional<Feed> findByIdAndStatusNot(Long id, Status status);

    List<Feed> findAllByBoard_idAndStatus(Long boardId, Status status);

    List<Feed> findAllByStatus(Status status);

//    TODO : 추후 전체 게시판을 추가한다면..
//    List<Feed> findAllByStatus(Status status);

}
