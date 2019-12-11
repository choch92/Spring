package naver.choch92.view.interceptor;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//객체가 자동 생성되고 bean 아이디는 logInterceptor
@Component
public class LogInterceptor implements HandlerInterceptor {
	
	// 요청을 Controller가 처리하기 전에 호출되는 메소드
	// true를 리턴하면 Controller에게 요청이 전달되고 false를 리턴하면 Controller에게 요청이 전달되지 않습니다.
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) {
		// 현재 시간을 request에 저장
		request.setAttribute("starttime", System.currentTimeMillis());
		
		// 오늘 날짜에 해당하는 파일명 만들기
		Calendar cal = new GregorianCalendar();
		java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
		String filename = today.toString() + ".log";

		// 파일에 출력하기 위한 파일 스트림을 생성
		// 파일이 존재하면 추가하고 없으면 생성
		// java1.7부터 사용 가능한 문법으로 close를 할 필요가 없습니다.
		try(FileOutputStream fos = 
				new FileOutputStream("C:\\Users\\a\\Desktop\\practice\\" + filename, true);
				PrintWriter pw = new PrintWriter(fos);) {
			// 현재 날짜 및 시간을 갖는 객체 생성
			java.util.Date date = new java.util.Date();
			// 파일에 기록
			pw.println(request.getRequestURI() + " " + date.toString());
			pw.flush();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		// Controller에게 요청을 처리하도록 설정
		return true;
	}
	
	// Controller가 요청을 정상적으로 처리한 후에 호출되는 메소드
	@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			ModelAndView modelAndView) {
		// 이전의 요청을 처리하기 전에 저장한 데이터를 가져오기
		long starttime = (Long)request.getAttribute("starttime");
		// 현재 시간 가져오기
		long endtime = System.currentTimeMillis();
		// 걸린 시간 알아보기
		System.out.println(request.getRequestURI() + "요청 처리 시간 : " + (endtime-starttime));
	}
	
	// Controller에서 요청을 처리하면 무조건 호출되는 메소드
	@Override
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception e) {
		System.err.println("무조건 호출됩니다.");
	}
	
}
