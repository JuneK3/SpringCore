package com.rootlab.core.singleton;

import com.rootlab.core.AppConfig;
import com.rootlab.core.member.MemberRepository;
import com.rootlab.core.member.MemberService;
import com.rootlab.core.member.MemberServiceImpl;
import com.rootlab.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {
	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		//모두 같은 인스턴스를 참고하고 있다.
		System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
		System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);
		//모두 같은 인스턴스를 참고하고 있다.
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//AppConfig도 스프링 빈으로 등록된다.
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean.getClass());
	}
}
