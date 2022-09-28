package com.rootlab.core;

import com.rootlab.core.discount.DiscountPolicy;
import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.discount.RateDiscountPolicy;
import com.rootlab.core.member.MemberRepository;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import com.rootlab.core.member.MemoryMemberRepository;
import com.rootlab.core.order.OrderService;
import com.rootlab.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public MemberService memberService() {
		System.out.println("AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
//		return null;
	}

	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
