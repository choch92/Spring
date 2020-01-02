package naver.choch92.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.choch92.board.dao.ItemDAO;
import naver.choch92.board.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDao;

	@Override
	public List<Item> allItem() {
		return itemDao.allItem();
	}

	@Override
	public Item getItem(int itemid) {
		return itemDao.getItem(itemid);
	}
}
