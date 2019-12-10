package naver.choch92.view.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import naver.choch92.view.domain.Member;

//Member 클래스에 대한 유효성 검사 클래스
public class MemberValidator implements Validator {

	// 어떤 클래스를 유효성 검사할 것인지 설정 하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		// Member 클래스의 유효성 검사를 하겠다. 라는 의미
		// clazz에 Member 클래스의 객체를 할당할 수 있도록 해주는 설정
		return Member.class.isAssignableFrom(clazz);
	}

	// 유효성 검사 메소드
	@Override
	public void validate(Object target, Errors errors) {
		// target을 유효성 검사할 객체로 형변환
		Member member = (Member) target;

		// 아이디는 null일수 없고 글자 수가 좌우 공백을 제외한 0일 수 없다.
		if (member.getId() == null && member.getId().trim().length() <= 0) {
			// 에러 메시지 설정
			// required.id라는 메시지를 사용
			errors.rejectValue("id", "required");
			return;
		}
		// 비밀번호는 7자 이상
		if (member.getPw().trim().length() < 7) {
			// 에러 메시지 설정
			// required.id라는 메시지를 사용
			errors.rejectValue("pw", "short");
		}
		String pw = member.getPw();
		char[] specials = { '@', '#', '$', '%' };
		int so = 0;
		int Dae = 0;
		int su = 0;
		int etc = 0;
		for (int i = 0; i < pw.length(); i = i + 1) {
			char ch = pw.charAt(i);
			if (ch >= '0' && ch <= '9') {
				su = su + 1;
			}
			if (ch >= 'a' && ch <= 'z') {
				so = so + 1;
			}
			if (ch >= 'A' && ch <= 'Z') {
				Dae = Dae + 1;
			}
			for (char t : specials) {
				if (ch == t) {
					etc = etc + 1;
					break;
				}
			}
		}
		if (so == 0) {
			errors.rejectValue("pw", "specialso");
		} else if (Dae == 0) {
			errors.rejectValue("pw", "specialdae");
		} else if (su == 0) {
			errors.rejectValue("pw", "specialsu");
		} else if (etc == 0) {
			errors.rejectValue("pw", "specialetc");
		}
	}
}
