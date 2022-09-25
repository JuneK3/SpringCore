package com.rootlab.core;

import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import com.rootlab.core.member.MemoryMemberRepository;
import com.rootlab.core.order.OrderService;
import com.rootlab.core.order.OrderServiceImpl;

public class AppConfig {

	public MemberService memberService() {
		return new MemberServiceImpl(new MemoryMemberRepository());
	}

	public OrderService orderService() {
		return new OrderServiceImpl(
				new MemoryMemberRepository(),
				new FixDiscountPolicy());
	}
}
