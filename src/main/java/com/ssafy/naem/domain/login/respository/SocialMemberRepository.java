package com.ssafy.naem.domain.login.respository;


import com.ssafy.naem.domain.member.entity.Member;
import com.ssafy.naem.domain.member.entity.SocialType;
import com.ssafy.naem.domain.oauth.entity.SocialMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialMemberRepository extends JpaRepository<SocialMember, Long> {
    Optional<SocialMember> findByMemberAndSocialType(Member oauthMember, SocialType socialType);
}
