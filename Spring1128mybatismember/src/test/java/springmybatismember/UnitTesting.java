package springmybatismember;

import org.junit.Test;

import naver.choch92.domain.Member;

public class UnitTesting {
	@Test
	public void testing() {
		Member member = new Member();
		member.setEmail("choch92@naver.com");
		member.setName("조씨가문");
		System.err.println(member);
	}
}
