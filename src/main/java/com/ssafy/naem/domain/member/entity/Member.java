package com.ssafy.naem.domain.member.entity;

import com.ssafy.naem.domain.oauth.entity.SocialMember;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "profile", length = 256)
    private String profile;

    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    @CreatedDate
    @Column(name = "create_date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDate createDate;

    @LastModifiedDate
    @Column(name = "modify_date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime modifyDate;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private boolean isDeleted;

//    @OneToOne(mappedBy = "member")
//    GeneralMember generalMember;

    @OneToMany(mappedBy = "member")
    List<SocialMember> socialMember;


    @Builder
    private Member(String name, String email, String profile, String refreshToken) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}