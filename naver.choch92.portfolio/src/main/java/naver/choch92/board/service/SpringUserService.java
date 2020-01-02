package naver.choch92.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.choch92.board.domain.SpringUser;

public interface SpringUserService {

	// 서비스의 메소드는 파라미터를 Controller가 읽을 지 Service가 읽을 지에 따라서
	// 메소드의 매개변수가 달라집니다.
	// Controller가 읽으면 변환된 파라미터가 매개변수이고 
	// Service에서 읽고자 할 때는 HttpServletRequest입니다.
	
	// 이메일 중복 검사를 위한 메소드
	public boolean emailCheck(String email);
	
	// 닉네임 중복 검사를 위한 메소드
	public boolean nicknameCheck(HttpServletRequest request);
	
	// 회원가입 처리를 위한 메소드
	// 파라미터를 서비스에 읽기 - 파일업로드가 있어서 Multipart을 추가
	public void join(MultipartHttpServletRequest request);
	
	// 로그인 처리 메소드
	public boolean login(HttpServletRequest request);
	
	// 위도와 경도를 받아서 주소를 리턴해주는 메소드
	public Map<String, Object> address(HttpServletRequest request);
	
	// 회원정보 수정을 위한 메소드를 선언
	public int update(MultipartHttpServletRequest request);
	
	// pwcheck POST에 이메일과 비밀번호를 리턴해주는 메소드
	public boolean login(SpringUser user);
	
	// 회원탈퇴 처리를 위한 메소드를 선언
	public int delete(HttpServletRequest request);
}
