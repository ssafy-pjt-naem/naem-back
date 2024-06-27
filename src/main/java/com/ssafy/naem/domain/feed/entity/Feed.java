package com.ssafy.naem.domain.feed.entity;

import com.ssafy.naem.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="board_id")
    private Board board;

//    TODO : 회원 추가 시 추가할 것
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(nullable=true)
    private LocalDateTime updated;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.STATUS_ACTIVE;

    @Column(nullable = false)
    @Builder.Default
    private Long views = 0L;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
        views = 0L;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }


    public void changeContents(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

    public void changeBoard(Board toBoard) {
        this.board = toBoard;
    }

    public void updateStatusToHiddenOrActive() {
        if (this.status.equals(Status.STATUS_ACTIVE)) {
            this.status = Status.STATUS_HIDDEN;
        } else if (this.status.equals(Status.STATUS_HIDDEN)) {
            this.status = Status.STATUS_ACTIVE;
        }
    }
    public void updateStatusToDeleted() {
        this.status = Status.STATUS_DELETED;
    }
}
