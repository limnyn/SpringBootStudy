package com.naver.shopping.autoScan;

import com.naver.shopping.AutoAppConfig;
import com.naver.shopping.discount.DiscountPolicy;
import com.naver.shopping.discount.SubDiscountPolicy;
import com.naver.shopping.member.Grade;
import com.naver.shopping.member.Member;
import com.naver.shopping.member.MemberRepository;
import com.naver.shopping.member.MemberService;
import com.naver.shopping.order.Order;
import com.naver.shopping.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class autoScanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + " object=" + bean);
        }
    }

    @Test
    @DisplayName("할인 정책 서비스의 할인 가격 확인")
    void getPrimaryDiscountPolicy() {
        Member testMember = new Member(1L, "tester", Grade.VIP);
        ac.getBean(MemberService.class).join(testMember);

        Order primaryServiceOrder = ac.getBean(OrderService1.class).createOrder(1L, "아이템1", 20000);
        Order qualifierServiceOrder = ac.getBean(OrderService2.class).createOrder(1L, "아이템1", 20000);

        System.out.println("primaryServiceOrder = " + primaryServiceOrder);
        System.out.println("qualifierServiceOrder = " + qualifierServiceOrder);

        Assertions.assertThat(primaryServiceOrder.getDiscountPrice()).isEqualTo(2000);
        Assertions.assertThat(qualifierServiceOrder.getDiscountPrice()).isEqualTo(1000);

    }

    @Component
    static class OrderService1 implements OrderService {
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        @Autowired
        public OrderService1(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }

        @Override
        public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member = memberRepository.findById(memberId);
            int discountPrice = discountPolicy.discount(member, itemPrice);
            return new Order(memberId, itemName, itemPrice, discountPrice);
        }
    }

    @Component
    static class OrderService2 implements OrderService{
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        @Autowired
        public OrderService2(MemberRepository memberRepository, @SubDiscountPolicy DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }

        @Override
        public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member = memberRepository.findById(memberId);
            int discountPrice = discountPolicy.discount(member, itemPrice);
            return new Order(memberId, itemName, itemPrice, discountPrice);
        }
    }
}