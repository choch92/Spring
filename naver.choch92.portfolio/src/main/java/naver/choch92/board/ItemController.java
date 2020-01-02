package naver.choch92.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import naver.choch92.board.domain.Item;
import naver.choch92.board.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping(value="/item/fruit", method=RequestMethod.GET)
	public String fruit(Model model) {
		// 서비스의 메소드를 호출
		List<Item> list = itemService.allItem();
		model.addAttribute("list", list);
		return "/item/fruit";
	}
	
	// detail/itemid 요청을 처리하는 메소드
	@RequestMapping(value="/item/detail/{itemid}", method=RequestMethod.GET)
	public String detail(@PathVariable int itemid, Model model) {
		// 서비스의 메소드를 호출
		Item item = itemService.getItem(itemid);
		// 데이터 저장
		model.addAttribute("item", item);
		return "/item/detail";
	}
}
