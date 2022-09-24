package com.rootlab.core.order;

import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	@Test
	void createOrder() {
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
