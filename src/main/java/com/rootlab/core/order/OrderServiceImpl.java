package com.rootlab.core.order;

import com.rootlab.core.discount.DiscountPolicy;
import com.rootlab.core.discount.FixDiscountPolicy;
import com.rootlab.core.member.Member;
import com.rootlab.core.member.MemberRepository;
import com.rootlab.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

	private MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		System.out.println("memberRepository in constructor = " + memberRepository);
		System.out.println("discountPolicy in constructor = " + discountPolicy);
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

//	@Autowired
//	public void setMemberRepository(MemberRepository memberRepository) {
//		System.out.println("memberRepository in setter = " + memberRepository);
//		this.memberRepository = memberRepository;
//	}

//	@Autowired
//	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//		System.out.println("discountPolicy in setter = " + discountPolicy);
//		this.discountPolicy = discountPolicy;
//	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
