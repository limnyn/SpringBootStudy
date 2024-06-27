package com.naver.shopping.order;

import com.naver.shopping.discount.DiscountPolicy;
import com.naver.shopping.discount.FixDiscountPolicy;
import com.naver.shopping.member.Member;
import com.naver.shopping.member.MemberRepository;
import com.naver.shopping.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}