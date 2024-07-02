package com.naver.shopping.member;


import com.naver.shopping.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test //테스트 코드 Annotation
    void 회원가입() {
        Member member = new Member(1L, "홍길동", Grade.VIP);
//        Member member1 = new Member(2L, "홍길동", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember); //assertThat의 왼쪽과 오른쪽을 isEqualTo로 비교하여 옳으면 확인한다.
//        Assertions.assertThat(member).isEqualTo(member1);
    }
}
