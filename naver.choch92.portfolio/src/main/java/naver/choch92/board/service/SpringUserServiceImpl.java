package naver.choch92.board.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.choch92.board.dao.SpringUserDAO;
import naver.choch92.board.domain.SpringUser;

//bean을 자동 생성해주고 Service 클래스라는 의미를 전달하기 위한 어노테이션
@Service
public class SpringUserServiceImpl implements SpringUserService {
	
	@Autowired
	private SpringUserDAO springUserDao;
	
	@Override
	public boolean emailCheck(String email) {
		boolean result = false;
		// email은 존재하지 않아야 중복검사를 통과
		String r = springUserDao.emailCheck(email);
		if(r == null) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean nicknameCheck(HttpServletRequest request) {
		boolean result = false;
		// 파라미터 읽기
		String nickname = request.getParameter("nickname");
		String r = springUserDao.nicknameCheck(nickname);
		if(r == null) {
			result = true;
		}
		return result;
	}

	@Override
	public void join(MultipartHttpServletRequest request) {
		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");	
		String phone = request.getParameter("phone");
		
		// 성별(라디오박스) 값 가져오기
		String gender = request.getParameter("gender");
		if("man".equals(gender)) {
			gender = "남자";
		}else {
			gender = "여자";
		}
		
		// 취미(체크 박스)의 값 가져와서 하나의 문자열로 만들기
		String [] hobbies = request.getParameterValues("hobby");
		// 각각의 문자열에 ,를 추가
		String hobby = "";
		for(String temp : hobbies) {
			hobby = hobby + temp + ",";
		}
		// 마지막 , 는 제거
		hobby = hobby.substring(0, hobby.length()-1);
		
		// 파일 이름 만들기와 업로드
		// file 파라미터의 값 가져오기
		MultipartFile f = request.getFile("image");
		// 원래 이름 가져오기
		String originName = f.getOriginalFilename();
		// 유일 무이한 파일이름 만들기
		String filename = email + originName;
		// 저장할 디렉토리 이름 만들기
		// 프로젝트 내의 경로
		// 실행하기 전에 프토젝트 내의 webapp(WebContext)안에 디렉토리를 생성
		String path = request.getServletContext().getRealPath("/userimage");
		// 업로드하는 파일이 있으면 저장하고 그렇지 않으면 default.png사용
		if(originName.length() > 0) {
			// 파일 업로드
			File file = new File(path + "/" + filename);
			try {
				f.transferTo(file);
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}else {
			filename = "default.png";
		}
		
		// year, month, day 값을 가지고 java.sql.Date 만들기
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
			
		Calendar cal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
		Date birthday = new Date(cal.getTimeInMillis());

		// DAO 메소드 호출
		SpringUser user = new SpringUser();
		user.setEmail(email);
		// 암호화해서 저장하기
		user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt(10)));
		user.setName(name);
		user.setNickname(nickname);
		user.setGender(gender);
		user.setImage(filename);
		user.setPhone(phone);
		user.setHobby(hobby);
		user.setBirthday(birthday);
		// 결과 리턴
		springUserDao.join(user);
	}

	@Override
	public boolean login(HttpServletRequest request) {
		boolean result = false;
		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		// email을 가지고 데이터 가져오기
		SpringUser user = springUserDao.login(email);
		
		// 세션에서 로그인 정보를 가진 키의 값을 삭제
		// home.jsp 파일의 <c:if test="${user == null}"> 구문 때문에 Attribute에 user기입
		request.getSession().removeAttribute("user");
		
		// email에 해당하는 데이터가 존재한다면
		if(user != null) {
			// 비밀번호 비교
			if(BCrypt.checkpw(pw, user.getPw())) {
				// session의 user에 사용자 정보 저장
				user.setPw(null);
				request.getSession().setAttribute("user", user);
				// 로그인 성공
				result = true;
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> address(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 파라미터 읽어오기
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		// 다운로드 받을 URL 생성
		String addr = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x="
				+ longitude + "&y=" + latitude + "&input_coord=WGS84";
		// 위의 주소에서 문자열을 다운로드 받기
		try {
			// URL을 생성하고 연결 객체를 생성
			URL url = new URL(addr);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			// 옵션 설정
			con.setUseCaches(false);
			con.setConnectTimeout(100000);
			// 헤더 설정
			con.addRequestProperty("Authorization", "KakaoAK abfe30c50bf8646b3518958413f646c1");
			// 문자열 다운로드
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			br.close();
			con.disconnect();
			
			String json = sb.toString();
			// json 파싱
			// 대괄호 벗기기 위해 사용
			JSONObject obj = new JSONObject(json);
			// 중괄호 벗기기 위해 사용
			JSONArray ar = obj.getJSONArray("documents");
			// 첫번째 데이터만 가져오기
			JSONObject item = ar.getJSONObject(0);
			
			// road_address 키의 값을 가져오기
			JSONObject road = item.getJSONObject("address");
			String roadaddress = road.getString("address_name");
			
			// Map에 저장
			map.put("address", roadaddress);
			
			// 배열을 순회
			/*
			for(int i=0; i<ar.length(); i=i+1) {
				JSONObject item = ar.getJSONObject(i);
			}
			*/

		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return map;
	}

	@Override
	public boolean login(SpringUser user) {
		boolean result = false;
		// 파라미터 읽기
		String email = user.getEmail();
		String pw = user.getPw();
		
		// email을 가지고 데이터 가져오기
		SpringUser user1 = springUserDao.login(email);
		
		// email에 해당하는 데이터가 존재한다면
		if(user1 != null) {
			// 비밀번호 비교
			if(BCrypt.checkpw(pw, user1.getPw())) {
				// 로그인 성공
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public int update(MultipartHttpServletRequest request) {
		int result = 0;
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		
		// 파일 이름 만들기와 업로드
		// file 파라미터의 값 가져오기
		MultipartFile f = request.getFile("image");
		// 원래 이름 가져오기
		String originName = f.getOriginalFilename();
		// 유일 무이한 파일이름 만들기
		String filename = email + originName;
		// 저장할 디렉토리 이름 만들기
		// 프로젝트 내의 경로
		// 실행하기 전에 프토젝트 내의 webapp(WebContext)안에 디렉토리를 생성
		String path = request.getServletContext().getRealPath("/userimage");
		// 업로드하는 파일이 있으면 저장하고 그렇지 않으면 default.png사용
		if(originName.length() > 0) {
			// 파일 업로드
			File file = new File(path + "/" + filename);
			try {
				f.transferTo(file);
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}else {
			filename = "default.png";
		}
		// DAO 메소드 호출
		SpringUser user = new SpringUser();
		user.setEmail(email);
		// 암호화해서 저장하기
		user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt(10)));
		user.setNickname(nickname);
		user.setImage(filename);

		result = springUserDao.update(user);
		
		return result;
	}

	@Override
	public int delete(HttpServletRequest request) {
		int result = -1;

		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		System.out.println(pw);
		
		HttpSession session = request.getSession();
		
		System.out.println(session);
		
		SpringUser user = (SpringUser)session.getAttribute("user");
		
		System.out.println(user);
		
		SpringUser loginUser = springUserDao.login(user.getEmail());
		
		System.out.println(loginUser);
				
		// email을 가지고 데이터 가져오기
		//SpringUser user = springUserDao.login(email);
				
		// email에 해당하는 데이터가 존재한다면
		if(loginUser != null) {
			// 비밀번호 비교
			if(BCrypt.checkpw(pw, loginUser.getPw())) {
				// session의 user에 사용자 정보 저장
				loginUser.setPw(null);
			}else {
				loginUser = null;
			}
		}
		if(loginUser != null) {
			result = springUserDao.delete(user.getEmail());
			System.out.println(result);
		}
		System.out.println(result);
		return result;
	}
}
