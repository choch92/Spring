package naver.choch92.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.choch92.dao.MemberDao;
import naver.choch92.dao.MemberMapper;
import naver.choch92.domain.Member;

// Bean을 자동생성하기 위한 어노테이션
@Service
public class MemberService {
	@Autowired
	// private MemberDao memberDao;
	private MemberMapper memberDao;
	
	// 데이터 삽입하는 메소드
	public boolean insert() {
		boolean result = false;
		
		// 파라미터를 생성
		Member member = new Member();
		member.setEmail("choch199293@naver.com");
		member.setPassword("1234");
		member.setName("admin");
		member.setPhone("01011114444");
		member.setNickname("다리우스너프점");
		member.setImage("default.png");
		Calendar cal = new GregorianCalendar(1992,0,27,11,23,18);
		Date birthday = new Date(cal.getTimeInMillis());
		// jdbc에서는 java.sql.Date를 사용, mybatis에서는 java.util.Date도 가능
		member.setBirthday(birthday);
		// DAO 메소드 호출
		int r = memberDao.insert(member);
		// 호출한 결과를 가지고 다른 클래스에 전달할 결과 데이터를 생성
		if(r>=0) {
			result = true;
		}		
		return result;
	}
	
	// 데이터 수정하는 메소드

	public boolean update() {
		boolean result = false;
		Member member = new Member();
		member.setEmail("choch199293@naver.com");
		member.setPassword("1234");
		member.setName("Customer");
		member.setPhone("01012121212");
		member.setNickname("github");
		member.setImage("default.png");
		Calendar cal = new GregorianCalendar(1993,12,12,11,23,18);
		Date birthday = new Date(cal.getTimeInMillis());
		// jdbc에서는 java.sql.Date를 사용, mybatis에서는 java.util.Date도 가능
		member.setBirthday(birthday);
		// DAO 메소드 호출
		int r = memberDao.update(member);
		// 호출한 결과를 가지고 다른 클래스에 전달할 결과 데이터를 생성
		if(r>=0) {
			result = true;
		}
		return result;
	}
	
	// 데이터 삭제하는 메소드
	public boolean delete() {
		boolean result = false;
		String email="choch199293@naver.com";
		int r = memberDao.delete(email);
		if(r>=0) {
			result = true;
		}
		return result;
	}
	
	// 데이터 전체를 가져오는 메소드
	public void allmember() {
		// 웹이면 뷰에서 데이터를 출력하기 위해서 저장하는 작업을 합니다.
		// 여러개를 리턴하는 메소드는 반복문을 사용할 수 있게 하기 위해서 검색된 데이터가 없으면 size=0의 형태로 리턴
		// NullPointerException을 만들지 않기 위해서
		List<Member> list = memberDao.allMember();
		for(Member member : list) {
			System.err.println(member);
		}
	}
	
	// 데이터 한개를 가져오는 메소드
	public void getMember() {
		System.err.println(memberDao.getMember("choch92@naver.com"));
	}

}
