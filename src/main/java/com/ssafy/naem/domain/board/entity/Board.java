package com.ssafy.naem.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @Column(nullable=true)
    private LocalDateTime updated;
    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.STATUS_ACTIVE;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }

    public void updateStatusToHidden() {
        this.status = Status.STATUS_HIDDEN;
    }
    public void updateStatusToDeleted() {
        this.status = Status.STATUS_DELETED;
    }
}
