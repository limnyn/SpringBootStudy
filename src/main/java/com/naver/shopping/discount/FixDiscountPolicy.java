package com.naver.shopping.discount;

import com.naver.shopping.member.Grade;
import com.naver.shopping.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    public int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
