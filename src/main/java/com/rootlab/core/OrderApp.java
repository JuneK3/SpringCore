package com.rootlab.core;

import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import com.rootlab.core.order.Order;
import com.rootlab.core.order.OrderService;
import com.rootlab.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

	public static void main(String[] args) {
//		AppConfig appConfig = new AppConfig();
//		OrderService orderService = appConfig.orderService();
//		MemberService memberService = appConfig.memberService();

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 20000);
		System.out.println("order: " + order);
	}

}
