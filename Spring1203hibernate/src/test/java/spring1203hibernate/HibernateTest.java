package spring1203hibernate;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.choch92.dao.ItemDao;
import naver.choch92.domain.Item;
import naver.choch92.service.ItemService;

//JUnit에서 설정 파일을 사용할 수 있도록 해주는 설정
@RunWith(SpringJUnit4ClassRunner.class)
// 스프링 설정 파일 실행
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class HibernateTest {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemService itemService;
	/**
	@Test
	public void hibernateTest() {

		// System.err.println(sessionFactory);
		Item item = new Item();
		item.setCode(100);
		item.setName("컴퓨터");
		// item.setManufacture("삼성");
		item.setManufacture("LG");
		// item.setPrice(1000000);
		item.setPrice(800000);
		// itemDao.insert(item);
		// itemDao.update(item);
		itemDao.delete(item);

		List<Item> list = itemDao.allItem();
		for(Item item : list) {
			System.err.println(item);
		}

		Item item = itemDao.getItem(200);
		System.err.println(item);
	}
**/
	@Test
	public void hibernateTest1() {
		// itemService.insert();
		// itemService.update();
		// itemService.delete();
		// itemService.list();
		itemService.get(1);
	}

}
