package naver.choch92.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naver.choch92.board.domain.SpringBoard;
import naver.choch92.board.domain.SpringUser;
import naver.choch92.board.service.SpringUserMobileService;
import naver.choch92.board.service.SpringUserService;

@RestController
public class JSONController {
	@Autowired
	private SpringUserService springUserService;
	
	// 이메일 중복검사를 위한 메소드
	@RequestMapping(value="user/emailcheck", method=RequestMethod.GET)
	public Map<String, Object> emailCheck(@RequestParam("email") String email){
		// 서비스 메소드 호출
		boolean result = springUserService.emailCheck(email);
		// 결과 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result + "");
		// 존재하는 이메일이면 result에 "false", 없는 email이면 result에 "true"가 저장됩니다.
		return map;
	}
	
	// 닉네임 중복검사를 위한 메소드
	// 파라미터를 Service에 읽도록 했기 때문에 매개변수가 HttpServletRequest
	@RequestMapping(value="user/nicknamecheck",method=RequestMethod.GET)
	public Map<String, Object> nicknameCheck(HttpServletRequest request){
		boolean result = springUserService.nicknameCheck(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result + "");
		return map;
	}
	
	@Autowired
	private SpringUserMobileService springUserMobileService;
	
	@RequestMapping(value="user/mobilelogin", method=RequestMethod.GET)
	public Map<String, Object> mobileLogin(HttpServletRequest request){
		// 리턴할 Map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		// 서비스 메소드 호출
		SpringUser user = springUserMobileService.login(request);
		if(user == null) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
			map.put("user", user);
		}
		return map;
	}
	
	// 주소를 리턴하는 요청 처리 메소드
	@RequestMapping(value="address", method=RequestMethod.GET)
	public Map<String, Object> address(HttpServletRequest request){
		return springUserService.address(request);
	}
	
	// board/the 요청이 왔을 때 세션이 가지고 있는 nmg 데이터를 리턴하는 메소드를 작성
	@RequestMapping(value="/board/the", method=RequestMethod.GET)
	public Map<String, Object> the(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		
		HttpSession session = request.getSession();
		List<SpringBoard> list = (List<SpringBoard>)session.getAttribute("nmg");
		List<SpringBoard> displist = new ArrayList<>();
		if(list.size() > 2) {
			map.put("d", "true");
			for(int i =0; i<2; i=i+1) {
				displist.add(list.remove(0));
			}
		}else {
			map.put("d", "false");
			int size = list.size();
			for(int i =0; i<size; i=i+1) {
				displist.add(list.remove(0));
			}
		}
		map.put("data", displist);
		
		return map;
	}

}
