package com.ssafy.domain.member.model;

import com.fasterxml.jackson.annotation.JsonTypeId;

import java.util.Date;

public class Member {

    private Long id;

    private String nickname;
    private Date created;
    private Date updated;
    private Status status;

}
