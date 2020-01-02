package naver.choch92.board.service;

import javax.servlet.http.HttpServletRequest;

import naver.choch92.board.domain.SpringUser;

//모바일 서비스를 위한 인터페이스
public interface SpringUserMobileService {
	// 로그인 처리를 위한 메소드
	public SpringUser login(HttpServletRequest request);
}

