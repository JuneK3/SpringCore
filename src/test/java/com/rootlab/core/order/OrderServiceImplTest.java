package com.rootlab.core.order;

import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {
	@Test
	void createOrder() {
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));
		OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
//		OrderServiceImpl orderService = new OrderServiceImpl();
//		orderService.setMemberRepository(memberRepository);
//		orderService.setDiscountPolicy(new FixDiscountPolicy());
		Order order = orderService.createOrder(1L, "itemA", 10000);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
