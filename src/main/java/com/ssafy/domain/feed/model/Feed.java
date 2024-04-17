package com.ssafy.domain.feed.model;

import com.ssafy.domain.board.model.Board;
import com.ssafy.domain.member.model.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false, length = 500)
    private String title;
    @Column(nullable = false, length = 5000)
    private String content;
    @Column(nullable = false, length = 50)
    private Date created;
    @Column(length = 50)
    private Date updated;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Category category;

}
