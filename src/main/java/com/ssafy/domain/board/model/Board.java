package com.ssafy.domain.board.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private Date created;
    @Column(length = 50)
    private Date updated;
    @Column(nullable = false)
    private Status status;

}
