package com.rootlab.core;

import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import com.rootlab.core.order.Order;
import com.rootlab.core.order.OrderService;
import com.rootlab.core.order.OrderServiceImpl;

public class OrderApp {

	public static void main(String[] args) {
		AppConfig appConfig = new AppConfig();
		OrderService orderService = appConfig.orderService();
		MemberService memberService = appConfig.memberService();

		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 20000);
		System.out.println("order: " + order);
	}

}
