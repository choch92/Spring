package naver.choch92.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.choch92.item.dao.GoodMapper;
import naver.choch92.item.dao.ItemDao;
import naver.choch92.item.dao.ItemHibernateDao;
import naver.choch92.item.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	// private ItemDao itemDao;
	// private GoodMapper itemDao;
	private ItemHibernateDao itemDao;
	
	@Transactional
	@Override
	public List<Item> allItem() {
		// 파라미터 읽기
		
		// 파라미터를 가지고 필요한 작업 수행 - Dao의 파라미터를 생성
		
		// Dao의 메소드를 호출해서 결과를 저장
		
		// 결과를 Controller에게 넘겨주도록 조작
		
		// 리턴
		return itemDao.allItem();
	}
	
	@Transactional
	@Override
	public Item getItem(int itemid) {
		return itemDao.getItem(itemid);
	}
}
