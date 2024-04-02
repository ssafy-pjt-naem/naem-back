package com.ssafy.domain.member.model;

import com.fasterxml.jackson.annotation.JsonTypeId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;
    @Column(nullable = false, length = 50)
    private Date created;
    @Column(length = 50)
    private Date updated;
    @Column(nullable = false)
    private Status status;

}
