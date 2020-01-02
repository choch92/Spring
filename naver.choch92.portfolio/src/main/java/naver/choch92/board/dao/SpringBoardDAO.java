package naver.choch92.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.choch92.board.domain.SpringBoard;

// 필수
@Repository
public class SpringBoardDAO {
	@Autowired
	// xml 을 이용한 MyBatis 사용
	private SqlSession sqlSession;
	// 인터페이스 이용한 MyBaits 사용할 때는 위의 구문 생략
	// 하이버네이트를 사용할 때는 변경
	// private SessionFactory sessionFactory
	
	// 글쓰기를 위한 SQL 실행
	public int write(SpringBoard board) {
		return sqlSession.insert("springboard.write", board);
	}
	
	// 가장 큰 글번호 찾아오는 SQL 실행
	public Integer maxNum() {
		return sqlSession.selectOne("springboard.maxnum");
	}
	
	// 목록보기를 위한 SQL 실행
	public List<SpringBoard> list(){
		return sqlSession.selectList("springboard.list");
	}
}
