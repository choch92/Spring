package naver.choch92.item;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 스프링에서 설정 파일(xml)을 이용하는 경우의 테스트 클래스
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBTest {
	// MyBatis 연동 객체를 주입
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void method() {
		System.err.println(sqlSession);
	}
}
