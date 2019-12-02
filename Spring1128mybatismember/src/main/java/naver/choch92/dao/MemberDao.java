package naver.choch92.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.choch92.domain.Member;

// 등록된 패키지 안에 있으면 bean을 자동으로 생성해주는 어노테이션
// @Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 데이터를 삽입하는 메소드
	public int insert(Member member) {
		return sqlSession.insert("member.insert", member);
	}
	
	// 데이터를 수정하는 메소드
	public int update(Member member) {
		return sqlSession.update("member.update", member);
	}
	
	// 데이터를 삭제하는 메소드
	public int delete(String email) {
		return sqlSession.delete("member.delete", email);
	}
	
	// 데이터 전체를 가져오는 메소드
	public List<Member> allmember(){
		return sqlSession.selectList("member.allmember");
	}
	
	// 한개의 데이터를 가져오는 메소드
	public Map<String, Object> getMember(String email){
		return sqlSession.selectOne("member.getmember", email);
	}
}
