package main;

import repository.DAO;
import repository.DAOFactory;
import vo.Item;

public class Main {

	public static void main(String[] args) {
		// 직접 객체 생성
		// 일반적인 경우 객체를 생성자를 호출해서 만들고 필요한 메소드를 호출
		// DAO dao = new DAO();
		// Item item = dao.get();
		// System.err.println(item);
		
		// 정수 상수의 해시코드
		int a = 10;
		int b = 10;
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));

		// Factory Pattern
		DAO dao = DAOFactory.create();
		Item item = dao.get();
		System.out.println(item);
		DAO dao1 = DAOFactory.create();		
		// 해시 코드 출력
		System.out.println(System.identityHashCode(dao));
		System.out.println(System.identityHashCode(dao1));
	}

}
