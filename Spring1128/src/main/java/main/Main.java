package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import dao.ItemDao;

public class Main {

	public static void main(String[] args) {
		// 스프링 설정 파일 호출하기
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		// 빈 가져오기
		ItemDao dao = context.getBean(ItemDao.class);
		// 전체 데이터 가져오기
		System.err.println(dao.list());
		// 1개 데이터 가져오기
		System.err.println(dao.get(1));
	}

}
