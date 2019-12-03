package naver.choch92.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import naver.choch92.dao.ItemDao;
import naver.choch92.domain.Item;

// @Component, @Controller, @Service, @Repository
// 위 4개의 어노테이션은 bean을 자동으로 생성해주는 어노테이션
// 트랜잭션의 적용은 Service에서 합니다.
@Service
public class ItemService {
	// 동일한 자료형의 bean이 있으면 아래 변수에 대입하도록 하는 설정
	@Autowired
	private ItemDao itemDao;
	
	// 데이터 삽입하는 메소드
	@Transactional
	public void insert() {
		Item item = new Item();
		item.setCode(200);
		item.setName("apple");
		item.setPrice(3000);
		item.setManufacture("seoul");
		itemDao.insert(item);
	}
	// 데이터 수정하는 메소드
	@Transactional
	public void update() {
		Item item = new Item();
		item.setCode(200);
		item.setName("banana");
		item.setPrice(5000);
		item.setManufacture("busan");
		itemDao.update(item);
	}
	// 데이터 삭제하는 메소드
	@Transactional
	public void delete() {
		Item item = new Item();
		item.setCode(200);
		itemDao.delete(item);
	}
	// 데이터 전체 가져오기 메소드
	@Transactional
	public void list() {
		List<Item> list = itemDao.allItem();
		for(Item item : list) {
			System.err.println(item);
		}
	}
	// 데이터 한개 가져오기 메소드
	@Transactional
	public void get(int code) {
		Item item = itemDao.getItem(code);
		System.err.println(item);
	}
}
