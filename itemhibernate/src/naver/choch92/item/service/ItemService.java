package naver.choch92.item.service;

import java.util.List;
import naver.choch92.item.domain.Item;

public interface ItemService {
	// 전체 데이터를 가져오는 메소드
	public List<Item> allItem();
	
	// 상세보기를 위한 메소드
	public Item getItem(int itemid);
}
