package naver.choch92.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import naver.choch92.domain.Member;

@Repository
public interface MemberMapper {
	@Insert("insert into member(email, password, name, phone, nickname, image, birthday) " 
			+ "values(#{email},#{password},#{name},#{phone},#{nickname},#{image},#{birthday})")
	public int insert(Member member);
	
	@Update("update member "
			+ "set password=#{password}, name=#{name}, phone=#{phone},"
			+ "nickname=#{nickname}, image=#{image}, birthday=#{birthday} "
			+ "where email=#{email}")
	public int update(Member member);
	
	@Delete("delete from member where email=#{email}")
	public int delete(String email);
	
	@Select("select * from member")
	public List<Member> allMember();
	
	@Select("select * from member where email=#{email}")
	public Member getMember(String email);

}
