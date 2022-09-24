package com.rootlab.core.order;

import com.rootlab.core.discount.DiscountPolicy;
import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemberRepository;
import com.rootlab.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
