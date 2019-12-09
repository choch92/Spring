package naver.choch92.view.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {

	// 다운로드 디렉토리 안의 파일 목록 메소드 구현
	@Override
	public List<String> fileList() {
		List<String> list = new ArrayList<>();
		// 파일 목록을 가져올 디렉토리를 설정
		File f = new File("C:\\Users\\a\\Downloads\\");
		// 디렉토리 안의 모든 파일과 디렉토리 이름 가져오기
		String[] s = f.list();
		// 디렉토리가 아닌 이름들만 list에 추가
		// 원래는 이름을 가지고 File 객체를 만든 후 isDirectory 함수로 확인을 해야 하는데
		// 확장자가 없는 것은 디렉토리
		for (String temp : s) {
			if (temp.indexOf(".") > 0) {
				list.add(temp);
			}
		}
		return list;
	}

	// 다운로드 디렉토리 안의 디렉토리 목록 메소드 구현
	@Override
	public List<String> dfileList() {
		List<String> list = new ArrayList<>();
		File f = new File("C:\\Users\\a\\Downloads\\");
		String[] s = f.list();
		for (String temp : s) {
			if (temp.indexOf(".") < 0) {
				list.add(temp);
			}
		}
		return list;
	}

	// 다운로드 디렉토리 안의 디렉토리 안의 파일 목록 메소드 구현
	@Override
	public List<String> directList(String direct) {
		List<String> list = new ArrayList<>();
		File f = new File("C:\\Users\\a\\Downloads\\" + direct);
		String[] s = f.list();
		for (String temp : s) {
			if (temp.indexOf(".") > 0) {
				list.add(temp);
			}
		}
		return list;
	}

}
