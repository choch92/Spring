package naver.choch92.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import naver.choch92.board.domain.SpringUser;
import naver.choch92.board.service.SpringUserService;

@Controller
public class UserController {
	// user/join 이라는 요청이 온 경우 처리하는 메소드
	// 단순한 페이지 이동만 수행
	@RequestMapping(value = "user/join", method = RequestMethod.GET)
	public String join(Model model) {
		return "user/join";
	}

	@Autowired
	private SpringUserService userService;

	// 회원 가입 처리를 위한 메소드
	// RedirectAttributes는 스프링이 제공하는 1회용 데이터 저장 클래스
	// 리다이렉트 할 때 1번만 사용하고자 하는 데이터가 있을 때 사용
	// 주로 메시지 저장할 때 사용
	@RequestMapping(value = "user/join", method = RequestMethod.POST)
	public String join(MultipartHttpServletRequest request, RedirectAttributes attr) {
		// 서비스의 메소드 호출
		userService.join(request);
		// 데이터 저장 - 1회용
		attr.addFlashAttribute("msg", "회원가입에 성공하셨습니다.");
		// 로그인 페이지로 리다이렉트
		return "redirect:login";
	}

	// 로그인 페이지로 이동하는 처리
	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "user/login";
	}

	// 로그인 페이지로 이동하는 처리
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request, RedirectAttributes attr) {
		// 서비스 메소드 호출
		boolean result = userService.login(request);
		if (result == true) {
			// 메인 페이지로 리다이렉트
			return "redirect:/";
		} else {
			// 로그인 페이지로 이동
			attr.addFlashAttribute("msg", "없는 아이디이거나 틀린 비밀번호 입니다.");
			return "redirect:/user/login";
		}
	}

	// 로그아웃 처리를 하는 메소드
	@RequestMapping(value = "user/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes attr) {
		// 로그아웃 처리 - 세션을 초기화 해도 되고 세션에서 user만 초기화 해도 됩니다.
		// session.invalidate();
		session.removeAttribute("user");
		attr.addFlashAttribute("msg", "로그아웃 하셨습니다.");
		return "redirect:login";
	}

	// 회원 정보 수정을 처리하는 메소드
	@RequestMapping(value = "user/update", method = RequestMethod.GET)
	public String update(Model model, HttpSession session) {
		// 로그인이 되어 있는 상태가 아니면 로그인 페이지로 이동
		if (session.getAttribute("user") == null) {
			return "redirect:login";
		}
		// 로그인 중이면 비밀번호를 확인하는 페이지로 이동
		else {
			return "redirect:pwcheck";
		}
	}

	@RequestMapping(value = "user/pwcheck", method = RequestMethod.GET)
	public String pwcheck(Model model) {
		return "user/pwcheck";
	}

	// 비밀번호 확인 요청을 처리하는 메소드를 생성
	@RequestMapping(value = "user/pwcheck", method = RequestMethod.POST)
	public String pwcheck(SpringUser user, HttpSession session, RedirectAttributes attr) {
		// 데이터에 저장되어 있는 이메일 불러오기
		SpringUser loginUser = (SpringUser) session.getAttribute("user");
		// 불러온 이메일을 user에 넣기
		user.setEmail(loginUser.getEmail());
		// ServiceImpl에 데이터에 이메일이 있으면 비밀번호를 비교하여 boolean값으로 리턴한 것을 userXO에 넣기
		boolean userXO = userService.login(user);
		if (userXO == false) {
			attr.addFlashAttribute("msg", "비밀번호가 잘못되었습니다.");
			return "redirect:/user/pwcheck";
		}
		return "redirect:updateform";
	}

	@RequestMapping(value = "user/updateform", method = RequestMethod.GET)
	public String updateform() {
		return "user/updateform";
	}

	@RequestMapping(value = "user/updateform", method = RequestMethod.POST)
	public String updateform(Model model, MultipartHttpServletRequest request, RedirectAttributes attr) {
		// 서비스 메소드 호출
		int result = userService.update(request);
		if (result > 0) {
			request.getSession().removeAttribute("user");
			attr.addFlashAttribute("update", "success");
			return "redirect:/";
		} else {
			return "redirect:/user/updateform";
		}
	}

	// 회원탈퇴 요청이 왔을 때 비밀번호 입력 페이지로 이동하는 메소드를 작성
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public String delete(Model model, HttpSession session) {
		// 세션에 데이터가 저장되어 있지 않을 때 로그인 페이지로 이동
		//if (session.getAttribute("user") == null) {
			//return "redirect:login";
		//}
		// 세션에 데이터가 저장되어 있고 로그인이 되어있을 때 비밀번호 입력 페이지로 이동
		//else {
			return "user/delete";
		//}
	}

	// 회원탈퇴 처리를 위한 메소드를 구현 (POST)
	@RequestMapping(value = "user/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, Model model,RedirectAttributes attr) {
		int r = userService.delete(request);
		if(r >= 0) {
			request.getSession().removeAttribute("user");
			attr.addFlashAttribute("delete", "success");
			return "redirect:/";
		}else {
			attr.addFlashAttribute("msg", "비밀번호가 잘못되었습니다.");
			return "redirect:delete";
		}
	}
}
