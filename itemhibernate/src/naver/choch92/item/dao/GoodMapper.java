package naver.choch92.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import naver.choch92.item.domain.Item;

// mapper 파일과 DAO 클래스의 역할을 모두 수행하는 인터페이스
@Repository
public interface GoodMapper {
	// 전체 데이터를 가져오는 메소드
	@Select("select * from item")
	public List<Item> allItem();
	
	// itemid를 가지고 하나의 데이터를 가져오는 메소드
	@Select("select * from item where itemid=#{itemid}")
	public Item getItem(int itemid);
}
