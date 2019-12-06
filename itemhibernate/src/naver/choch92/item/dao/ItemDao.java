package naver.choch92.item.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.choch92.item.domain.Item;

@Repository
public class ItemDao {
	@Autowired
	private SqlSession sqlSession;
	
	// Item 테이블에서 전체 데이터를 가져오는 메소드
	// 여러 개의 행이 나오는 경우는 List<resultType>
	// selectList를 호출하고 sql의 id 그리고 파라미터를 대입
	public List<Item> allItem(){
		return sqlSession.selectList("item.allitem");
	}
	
	// itemid를 가지고 하나의 데이터를 가져오는 메소드
	// 0개나 1개의 행만 리턴하는 경우는 resultType
	// selectOne을 호출하고 sql의 id 그리고 파라미터를 대입
	public Item getItem(int itemid) {
		return sqlSession.selectOne("item.getitem", itemid);
	}
}
