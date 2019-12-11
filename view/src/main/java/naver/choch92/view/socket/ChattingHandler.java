package naver.choch92.view.socket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChattingHandler extends TextWebSocketHandler {
	// 클라이언트들의 웹 소켓 세션을 저장할 List를 생성
	// private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();

	// 단순한 채팅이 아니고 그룹 채팅이나 귓말 등이 가능하도록 하려면
	// WebSocketSession 대신에 Map이나 DTO를 이용
	private static Map<String, WebSocketSession> map = 
			new HashMap<String, WebSocketSession>();
	
	@Override
	// 클라이언트가 연결되었을 때 호출될 메소드
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception{
		log(webSocketSession.getId() + "연결됨");
		// 클라이언트를 Map에 저장
		map.put(webSocketSession.getId(), webSocketSession);
		log("1" + map.values());
	}
	
	@Override
	// 클라이언트의 접속이 종료되었을 때 호출될 메소드
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
		log(webSocketSession.getId() + "연결종료됨");
		// Map에서 제거
		map.remove(webSocketSession.getId());
	}
	
	@Override
	// 클라이언트가 메시지를 전송했을 때 호출되는 메소드
	public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
		// 전송된 메시지 가져오기
		// String msg = message.getPayload();
		log(webSocketSession.getId() + "로부터 메시지 수신 :" + message.getPayload());
		// 접속한 사용자의 아이디를 제거후 메시지 보내기
		// TextMessage msg = new TextMessage(message.getPayload().substring(4));
		TextMessage msg = new TextMessage(message.getPayload());
		
		// Map의 모든 세션에게 메시지를 전송
		try {
			for(WebSocketSession ses : map.values()) {
				ses.sendMessage(msg);
				log(ses.getId() + "에 메시지 발송:" + message.getPayload());
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void log(String logmsg) {
		System.out.println(logmsg);
	}
}
