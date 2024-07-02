package com.naver.shopping;

import com.naver.shopping.discount.RateDiscountPolicy;
import com.naver.shopping.member.MemberRepository;
import com.naver.shopping.member.MemberService;
import com.naver.shopping.member.MemberServiceImpl;
import com.naver.shopping.member.MemoryMemberRepository;
import com.naver.shopping.order.OrderService;
import com.naver.shopping.order.OrderServiceImpl;
import com.naver.shopping.discount.DiscountPolicy;
import com.naver.shopping.discount.FixDiscountPolicy;

public class AppConfig {
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}