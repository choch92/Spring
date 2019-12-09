package naver.choch92.view.service;

import java.util.List;

public interface ViewService {
	// 다운로드 디렉토리의 파일 목록을 리턴할 메소드
	public List<String> fileList();
	
	// 다운로드 디렉토리의 디렉토리 목록을 리턴할 메소드
	public List<String> dfileList();
	
	// 디렉토리안에 디렉토리를 다운로드 받을 메소드를 생성
	public List<String> directList(String direct);
	
}
