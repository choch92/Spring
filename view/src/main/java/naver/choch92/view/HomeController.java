package naver.choch92.view;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.choch92.view.domain.Birth;
import naver.choch92.view.domain.Book;
import naver.choch92.view.domain.BookReport;
import naver.choch92.view.domain.Member;
import naver.choch92.view.service.ViewService;
import naver.choch92.view.validator.BirthValidator;
import naver.choch92.view.validator.MemberValidator;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	// Controller에서는 Service를 주입받아야 합니다.
	@Autowired
	private ViewService viewService;

	@RequestMapping(value = "fileview", method = RequestMethod.GET)
	public String fileview(Model model) {
		// 디렉토리 안의 파일 목록을 가져오는 서비스 메소드를 호출
		List<String> list = viewService.fileList();
		// 디렉토리 안의 디렉토리 목록을 가져오는 서비스 메소드를 호출
		List<String> flist = viewService.dfileList();

		// 뷰에게 전달할 데이터를 저장
		model.addAttribute("flist", list);
		model.addAttribute("dlist", flist);

		return "fileview";
	}

	// download?filename=#{name} 이 요청을 처리하는 메소드
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String download(Model model, @RequestParam("filename") String filename) {
		// 다운로드 받을 파일 객체를 생성
		File file = new File("/Users/a601/Downloads" + filename);
		// 다운로드 받을 파일을 저장해서 뷰로 전송
		model.addAttribute("file", file);
		return "download";
	}

	// download?direct=#{name} 이 요청을 처리하는 메소드
	@RequestMapping(value = "directList", method = RequestMethod.GET)
	public String directList(Model model, @RequestParam("direct") String direct) {
		List<String> list = viewService.directList(direct);
		model.addAttribute("detail", list);
		return "directList";
	}
	
	// excel.xls 요청을 처리하는 메소드
	@RequestMapping(value = "excel.xls", method=RequestMethod.GET)
	public String excel(Model model) {
		// 서비스의 메소드를 호출해서 데이터를 가져와야 하지만 편의상 직접 생성
		List<String> list = new ArrayList<>();
		list.add("캡슐화");
		list.add("상속성");
		list.add("다형성");
		
		model.addAttribute("oop", list);
		return "excel";
	}
	
	// oop.pdf 요청을 처리하는 메소드
	@RequestMapping(value = "oop.pdf", method = RequestMethod.GET)
	public String pdf(Model model) {
		// 서비스의 메소드를 호출해서 데이터를 가져와야 하지만 편의상 직접 생성
		List<String> list = new ArrayList<>();
		list.add("Encapsulateion - 캡슐화");
		list.add("Inheritance - 상속성");
		list.add("Polymorphism - 다형성");
		
		model.addAttribute("oop", list);		
		return "pdf";
	}
	
	@RequestMapping(value = "view.json", method = RequestMethod.GET)
	public String json(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "겨울왕국2");
		map.put("company", "디즈니");
		map.put("language", "English");
		map.put("production cost", "2억달러");
		map.put("box revenue" , "4억8천달러");
		
		model.addAttribute("data", map);
		return "json";
	}
	
	@RequestMapping(value = "book.xml", method = RequestMethod.GET)
	public String xml(Model model) {
		// 출력할 데이터 생성
		BookReport report = new BookReport();
		
		Book book = new Book();
		book.setTitle("삼국지");
		book.setAuthor("나관중");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		
		book = new Book();
		book.setTitle("군주론");
		book.setAuthor("마키아벨리");
		list.add(book);
		
		report.setList(list);
		model.addAttribute("list", report);
		return "xml";
	}
	// add라는 요청에 first 와 second 라는 파라미터가 있는 요청을 처리하는 메소드
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(@RequestParam("first")int first,
			@RequestParam("second")int second, Model model) {
		int result = first + second;
		model.addAttribute("result", result);
		return "result";
	}
	// Controller에 Exception이 발생하면 return하는 뷰를 출력하도록 하는 설정
	@ExceptionHandler(Exception.class)
	public String exception(Model model) {
		model.addAttribute("msg", "빠른 시간에 처리하겠습니다.");
		return "error/exception";
	}
	
	// 단순 페이지 이동의 경우는 매개변수(Model mode)없이 포워딩할 페이지 이름만 리턴하면됨
	@RequestMapping(value = "login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	// join 요청이 GET 방식으로 온 경우 처리
	// Spring 유효성 검사를 사용하는 경우에는 DTO 객체를 매개변수로 생성
	// Member 객체가 넘어갈때 command라는 이름으로 넘어갑니다.
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join(@ModelAttribute("command") Member member) {
		return "join";
	}
	
	// join 요청을 POST 방식으로 요청했을 때 호출되는 메소드
	// form의 데이터를 전송할 때는 BindingResult를 만들어서 유효성 검사의 결과를 저장하도록 합니다.
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(@ModelAttribute("command") Member member, BindingResult result) {
		// 유효성 검사를 수행
		// member를 가지고 유효성 검사를 해서 결과를 result에 저장
		new MemberValidator().validate(member, result);
		// 유효성 검사를 통과하지 못한 경우
		if(result.hasErrors()) {
			return "join";
		}
		return "joincomplete";
	}
	// birth 요청이 GET 방식으로 온 경우 처리
	@RequestMapping(value="birth", method=RequestMethod.GET)
	public String birth(@ModelAttribute("command") Birth birth) {
		return "birth";
	}
	// birth 요청이 POST 방식으로 온 경우 처리
	@RequestMapping(value="birth", method=RequestMethod.POST)
	public String birth(@ModelAttribute("command") Birth birth, BindingResult result) {
		new BirthValidator().validate(birth, result);
		if(result.hasErrors()) {
			return "birth";
		}
		return "birthcomplete";
	}
	
	// fileupload 요청이 GET 방식으로 온 경우 처리
	@RequestMapping(value="fileupload", method=RequestMethod.GET)
	public String fileupload() {
		return "fileupload";
	}
	// fileupload 요청이 POST 방식으로 온 경우 처리
	@RequestMapping(value="upload", method=RequestMethod.POST)
	// MultipartHttpServletRequest를 이용하는 방법
	public String upload(MultipartHttpServletRequest request) {
		// file 파라미터의 데이터 가져오기
		MultipartFile file = request.getFile("file");
		// 파일 업로드 : 문서 디렉토리
		String filepath = "C:\\Users\\a\\Desktop\\practice\\";
		// 저장 경로와 원래의 파일 이름을 결합
		// 파일 이름의 중복을 제거하기 위해서 랜덤한 문자열을 추가(UUID.randomUUID().toString())
		filepath = filepath + 
				UUID.randomUUID().toString() + file.getOriginalFilename();
		try {
			// 파일 업로드
			file.transferTo(new File(filepath));
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return "complete";
	}
	
	// echo 요청이 GET 방식으로 온 경우 처리
	@RequestMapping(value="echo", method=RequestMethod.GET)
	public String echo() {
		return "echo";
	}
	
	// chat 요청이 GET 방식으로 온 경우 처리
	@RequestMapping(value="chat", method=RequestMethod.GET)
	public String chat() {
		return "chat";
	}

}
