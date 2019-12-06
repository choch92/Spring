package naver.choch92.view.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {

	@Override
	public List<String> fileList() {
		List<String> list = new ArrayList<>();
		
		// 파일 목록을 가져올 디렉토리를 설정
		File f = new File("C:\\Users\\a\\Downloads\\");
		// 디렉토리 안의 모든 파일과 디렉토리 이름 가져오기
		String [] s = f.list();
		// 디렉토리가 아닌 이름들만 list에 추가
		// 원래는 이름을 가지고 File 객체를 만든 후 isDirectory 함수로 확인을 해야 하는데 
		// 확장자가 없는 것은 디렉토리
		String ex = "";
		for(String temp : s) {
			if(temp.indexOf(".") > 0) {
				list.add(temp);
			}else if(temp.indexOf(".") < 0) {
				ex = "(디렉토리 파일은 선택 안됨)" + temp;
				list.add(ex);
			}
		}
		return list;
	}

}
