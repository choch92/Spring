package naver.choch92.view;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import naver.choch92.view.service.ViewService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	// Controller에서는 Service를 주입받아야 합니다.
	@Autowired
	private ViewService viewService;
	@RequestMapping(value="fileview", method = RequestMethod.GET)
	public String fileview(Model model) {
		// 파일 목록을 가져오는 서비스 메소드 호출
		List<String> list = viewService.fileList();
		// 뷰에게 전달할 데이터 저장
		model.addAttribute("list", list);
		return "fileview";
	}
	// download?filename=#{name} 이 요청을 처리하는 메소드
	@RequestMapping(value="download", method = RequestMethod.GET)
	public String download(Model model, @RequestParam("filename") String filename) {
		// 다운로드 받을 파일 객체를 생성
		File file = new File("C:\\Users\\a\\Downloads\\" + filename);
		model.addAttribute("file", file);
		return "download";
	}
}
