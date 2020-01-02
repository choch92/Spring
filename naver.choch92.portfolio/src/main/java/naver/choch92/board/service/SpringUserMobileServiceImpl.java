package naver.choch92.board.service;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.choch92.board.dao.SpringUserDAO;
import naver.choch92.board.domain.SpringUser;

@Service
public class SpringUserMobileServiceImpl implements SpringUserMobileService {
	
	@Autowired
	private SpringUserDAO springUserDao;
	
	@Override
	public SpringUser login(HttpServletRequest request) {
		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		// email을 이용해서 데이터 찾아오기
		SpringUser user = springUserDao.login(email);
		if(user != null) {
			// 로그인 성공한 경우는 user를 리턴
			if(BCrypt.checkpw(pw, user.getPw())) {
				user.setPw(null);
				return user;
			}
		}
		// 로그인 실패한 경우에는 null를 리턴
		return null;
	}

}
 