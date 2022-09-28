package com.rootlab.core;

import com.rootlab.core.member.MemberRepository;
import com.rootlab.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
	//수동 빈 등록과 자동 빈 등록 충돌시 스프링 부트에서 에러 발생
//	@Bean(name = "memoryMemberRepository")
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//	}
}
