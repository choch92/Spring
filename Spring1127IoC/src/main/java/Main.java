import org.springframework.context.support.GenericXmlApplicationContext;

import repository.ItemDAO;

public class Main {

	public static void main(String[] args) {
		// xml 설정 객체 생성
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// xml 파일에 만든 bean 가져오기
		// 생성은 다르게 했지만 스프링이 관리하는 객체를 호출했기 때문에 새로 만들어서 리턴하는 것이 아니고
		// 미리 만들어 놓은 객체를 리턴하기 때문에 2개의 해시코드는 동일합니다.
		ItemDAO dao = context.getBean(ItemDAO.class);
		// ItemDAO dao = context.getBean("itemDAO", ItemDAO.class);
		ItemDAO dao1 = context.getBean(ItemDAO.class);
		
		System.out.println(System.identityHashCode(dao));
		System.out.println(System.identityHashCode(dao1));
		// 사용
		dao.getOne();
	}

}
