package com.rootlab.core.discount;

import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import org.springframework.stereotype.Component;

// RateDiscountPolicy와 FixDiscountPolicy를 모두 스프링 빈으로 등록하면
// DiscountPolicy 타입으로 빈을 조회할 경우 빈이 중복되는 문제가 발생하게 됨
@Component
public class FixDiscountPolicy implements DiscountPolicy {

	private int discountFixAmount = 1000;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		} else {
			return 0;
		}
	}
}
