package naver.choch92.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import naver.choch92.board.domain.SpringBoard;

public interface SpringBoardService {

	// 게시글 작성을 처리하기 위한 메소드
	public boolean write(HttpServletRequest request);
	
	// 게시글 작성된 목록을 보기위한 메소드
	public List<SpringBoard> list(HttpServletRequest request);

}
