package naver.choch92.view.validator;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import naver.choch92.view.domain.Birth;

public class BirthValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Birth.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 유효성 검사 수행할 객체로 변환
		Birth birth = (Birth) target;

		// 유효성 검사 실패 조건과 메시지를 설정
		int year = birth.getYear();
		Calendar cal = new GregorianCalendar();
		if (year < 1920 || year > cal.get(Calendar.YEAR)) {
			// error.year 메시지를 출력
			errors.rejectValue("year", "error");
		}
		// 월
		int month = birth.getMonth();
		if (month < 1 || month > 12) {
			errors.rejectValue("month", "error");
		}
		// 일
		int day = birth.getDay();
		if (day < 1 || day > 31) {
			errors.rejectValue("day", "error");
		}

	}

}
