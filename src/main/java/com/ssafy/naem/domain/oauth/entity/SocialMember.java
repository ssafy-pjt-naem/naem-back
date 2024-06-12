package com.ssafy.naem.domain.oauth.entity;

import com.ssafy.naem.domain.member.entity.Member;
import com.ssafy.naem.domain.member.entity.SocialType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class SocialMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialType socialType;

    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    private SocialMember(SocialType socialType, Member member, String providerId) {
        this.member = member;
        this.socialType = socialType;
        this.providerId = providerId;
    }
}