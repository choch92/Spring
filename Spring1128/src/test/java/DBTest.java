import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 테스트 클래스 설정
@RunWith(SpringJUnit4ClassRunner.class)
// 설정 파일을 실행하도록 설정
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class DBTest {
	// 동일한 자료형의 Bean이 있으면 자동 주입
		@Autowired
		private DataSource ds;
		
		// 테스트 메소드
		@Test
		public void testDB() {
			try {
				Connection con = ds.getConnection();
				System.out.println(con);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

}
