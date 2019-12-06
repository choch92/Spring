package naver.choch92.view.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	// 생성자 - ContentType을 생성
	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}
	@Override
	// model 이 Controller가 넘겨준 데이터를 소유한 맵
	// response가 응답객체
	// request는 요청 객체
	protected void renderMergedOutputModel(
			Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 다운로드 할 파일 객체를 찾아오기
		// HomeController에 있는 model.addAttribute("file", file); <-Key("file")
		File f = (File)model.get("file");
		// 뷰 형식과 파일 크기 설정
		response.setContentType(getContentType());
		response.setContentLength((int)f.length());
		// IE 와 다른 브라우저 구분
		// 운영 체제와 브라우저를 구분
		// 운영체제 이름과 브라우저 모델 이름이 포함되어 있습니다.
		// IE는 rv라는 문자를 포함하고 있습니다.
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("rv") > -1;
		
		// 파일 이름 설정 - 이 부분을 작성하지 않으면 한글로 이름이 만들어진 파일은 다운로드가 안되거나 깨짐
		String filename = null;
		if(ie) {
			filename = URLEncoder.encode(f.getName(), "utf-8");
		}else {
			filename = new String(f.getName().getBytes("utf-8"), "iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		// 파일 전송을 위한 부분 작성
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			// 파일의 내용을 읽고
			fis = new FileInputStream(f);
			// out에게 복사
			FileCopyUtils.copy(fis, out);
			fis.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		out.flush();
	}
}
