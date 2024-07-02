package com.naver.shopping.order;

import com.naver.shopping.AppConfig;
import com.naver.shopping.member.Grade;
import com.naver.shopping.member.Member;
import com.naver.shopping.member.MemberService;
import com.naver.shopping.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    /**
     * 테스트 파일의 이름은 "OrderServiceTest"로 할게요~
     * 테스트 함수의 이름은 "주문하기"로 해주시구요
     * Member테스트 할 때 만들었던 것 같이 사용자는 ID값 1의 "실험체1"이란 이름의 VIP 사용자입니다
     * 24,900원짜리 USB를 샀을 때 1,000원이 할인되는지 확인하는 코드를 짜주세요!!
     * */
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    void 주문하기_고정할인() {
        long memberId = 1L;
        Member member = new Member(memberId, "실험체1", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "USB", 24900);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void 주문하기_정률할인() {
        long memberId = 1L;
        Member member = new Member(memberId, "실험체1", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "USB", 24900);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2490);
    }
}
