package StepStoneChat.StepStoneChat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * socket 통신은 1:N 관계를 맺는다.
 * 한 서버에 여러 개의 클라이언트가 접속하기 때문에 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler의 작성이 필요하다.
 * client로 부터 받은 메시지를 Console Log에 출력하고, Client로 환영 메시지를 보낸다.
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        TextMessage textMessage = new TextMessage("Welcome chat server");
        session.sendMessage(textMessage);
    }
}
