package naver.choch92.springmvc;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.choch92.springmvc.domain.User;

// bean을 자동생성해주는 어노테이션
// 의미상 이 클래스의 객체는 Controller의 역할을 한다는 것을 알려줌
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// /(슬래시)요청이 GET 방식으로 왔을 때 처리하는 메소드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	// String을 리턴하면 리턴하는 문자열이 뷰의 이름이 됩니다.
	// View Resolver 와 WEB-INF/views/home.jsp가 출력하는 뷰의 이름이 됩니다.
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value="first", method = RequestMethod.GET)
	public String first(Model model) {
		// request에 데이터 저장
		model.addAttribute("language", "JAVA");
		// 출력할 뷰이름을 리턴
		return "first";
	}
	@RequestMapping(value="event", method = RequestMethod.GET)
	public String event(Model model) {
		model.addAttribute("data", "출력할 데이터");
		return "event";
	}
	// detail/123 요청을 처리하기 위한 메소드
	@RequestMapping(value="detail/{no}", method = RequestMethod.GET)
	// 주소의 마지막 부분을 정수로 형 변환해서 no에 저장
	public String detail(@PathVariable int no, Model model) {
		// no라는 key에 no 데이터를 저장
		model.addAttribute("no", no);
		return "detail";
	}
	// login요청이 POST로 왔을 때 처리하는 메소드
	@RequestMapping(value="login", method=RequestMethod.POST)
	/*
	// HttpServletRequest를 이용한 처리 방법
	public String login(HttpServletRequest request) {
		// 파라미터 읽기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.err.println("id : " + id);
		System.err.println("pw : " + pw);
		return "login";
	}
	*/
	// @RequestParam을 이용하는 방법
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		System.err.println("id : " + id);
		System.err.println("pw : " + pw);
		return "login";
	}
	/*
	// Command 객체를 이용하는 방법
	public String login(User user) {
		System.err.println(user);
		return "login";
	}
	*/
	@RequestMapping(value="json", method = RequestMethod.GET)
	public @ResponseBody User json() {
		User user = new User();
		user.setId("choch92");
		user.setPw("1234");
		return user;
	}
/*
	@RequestMapping(value="json", method = RequestMethod.GET)
	public @ResponseBody List<String> json() {
		List<String> list = new ArrayList<String> ();
		list.add("Java");
		return list;
	}
*/
	@RequestMapping(value="header", method = RequestMethod.GET)
	public ResponseEntity<String> header() {
		String msg = "{\"name\":\"cho\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(msg, headers, HttpStatus.OK);
	}

}
