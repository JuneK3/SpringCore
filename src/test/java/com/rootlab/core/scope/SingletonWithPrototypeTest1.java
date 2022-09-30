package com.rootlab.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
	@Test
	void prototypeFind() {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ClientAnotherBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		System.out.println("clientBean1 = " + clientBean1);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		System.out.println("clientBean2 = " + clientBean2);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(2);

		ClientAnotherBean anotherBean1 = ac.getBean(ClientAnotherBean.class);
		System.out.println("anotherBean1 = " + anotherBean1);
		count1 = anotherBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientAnotherBean anotherBean2 = ac.getBean(ClientAnotherBean.class);
		System.out.println("anotherBean2 = " + anotherBean2);
		count2 = anotherBean2.logic();
		assertThat(count2).isEqualTo(2);

	}

	// default Scope: singleton
	static class ClientBean {
		private final PrototypeBean prototypeBean;

		@Autowired
		ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}

		public int logic() {
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}

	}

	static class ClientAnotherBean {
		private final PrototypeBean prototypeBean;

		@Autowired
		ClientAnotherBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}

		public int logic() {
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}

	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
