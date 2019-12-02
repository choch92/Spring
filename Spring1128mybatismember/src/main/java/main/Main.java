package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import naver.choch92.service.MemberService;

public class Main {

	public static void main(String[] args) {
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		MemberService service = context.getBean(MemberService.class);
		// 메소드 호출
		// System.err.println(service.insert());
		// System.err.println(service.update());
		// System.err.println(service.delete());
		// service.allmember();
		service.getMember();
	}

}
