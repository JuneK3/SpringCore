package com.rootlab.core.autowired;

import com.rootlab.core.AutoAppConfig;
import com.rootlab.core.discount.DiscountPolicy;
import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.discount.RateDiscountPolicy;
import com.rootlab.core.member.Grade;
import com.rootlab.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllDiscountPolicyBeanTest {

	@Test
	void findAllDiscountPolicyBean() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
		assertThat(discountPrice).isEqualTo(1000);
		discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
		assertThat(discountPrice).isEqualTo(2000);
	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policies;

		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			System.out.println("policyMap = " + policyMap);
			System.out.println("policies = " + policies);
			this.policyMap = policyMap;
			this.policies = policies;
		}

		public int discount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			System.out.println("discountCode = " + discountCode);
			System.out.println("discountPolicy = " + discountPolicy);
			return discountPolicy.discount(member, price);
		}
	}
}
