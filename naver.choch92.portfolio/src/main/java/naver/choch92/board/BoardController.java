package naver.choch92.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import naver.choch92.board.domain.SpringBoard;
import naver.choch92.board.service.SpringBoardService;

@Controller
public class BoardController {
	@Autowired
	private SpringBoardService springBoardService;
	
	// board/write 요청이 GET 방식으로 온 경우 처리
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String write(Model model) {
		return "/board/write";
	}
	
	// board/write 요청이 POST 방식으로 온 경우 처리
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String write(HttpServletRequest request, RedirectAttributes attr) {
		boolean r = springBoardService.write(request);
		if(r == true) {
			return "redirect:/";
		}else {
			attr.addFlashAttribute("msg", "게시글 작성에 실패했습니다.");
			return "/board/write";
		}
	}
	
	// 목록보기 처리를 위한 메소드
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		// 서비스의 메소드 호출
		// 전체 데이터 가져오기
		List<SpringBoard> list = springBoardService.list(request);
		// 출력할 데이터 만들기
		List<SpringBoard> displist = new ArrayList<>();
		// 전체 데이터가 2개보다 많으면 같으면
		if(list.size() >= 2) {
			for(int i=0; i<2; i=i+1) {
				// list에서는 제거하면서 displist에 추가
				displist.add(list.remove(0));
			}
			// 남은 데이터를 세션에 nmg로 저장
			request.getSession().setAttribute("nmg", list);
			// 데이터가 더 존재한다는 표시를 합니다.
			request.setAttribute("d", "t");
		}else {
			displist = list;
		}
		// 데이터를 저장 - 출력할 데이터
		model.addAttribute("list", displist);
		return "board/list";
	}

}
