package StepStoneChat.StepStoneChat.config;

import StepStoneChat.StepStoneChat.domain.ChatMessage;
import StepStoneChat.StepStoneChat.domain.ChatRoom;
import StepStoneChat.StepStoneChat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("payload {}", payload);
////        TextMessage textMessage = new TextMessage("Welcome chat server");
////        session.sendMessage(textMessage);
//        //WebSocket Client로 부터 채팅 메시지를 전달받아 채팅 메시지 객체로 변환
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        //전달 받은 메시지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조회함
//        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
//        //해당 채팅방에 입장해 있는 모든 클라이언트들(WebSocket session)에게 타입에 따른 메시지 전송
//        room.handleActions(session, chatMessage, chatService);
    }
}
