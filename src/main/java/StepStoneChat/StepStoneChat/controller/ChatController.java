package StepStoneChat.StepStoneChat.controller;

import StepStoneChat.StepStoneChat.domain.ChatMessage;
import StepStoneChat.StepStoneChat.domain.ChatRoom;
import StepStoneChat.StepStoneChat.jwt.JwtTokenProvider;
import StepStoneChat.StepStoneChat.repository.ChatRoomRepository;
import StepStoneChat.StepStoneChat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/chats")
public class ChatController {

//    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        /**
         * jwtTokenProvider.getUserNameFromJwt(token)
         *
         * WebSocket을 통해 서버에 메시지가 Send(/pub/chat/message) 되었을 때도 Jwt Token 유효성 검증이 필요하다.
         * 다음과 같이 회원 대화명(nickname)를 조회하는 코드를 삽입하여 유효성이 체크될 수 있도록 한다.
         *
         * 유효하지 않은 JWT 토큰이 세팅될 경우 websocket을 통해 보낸 메시지는 무시된다.
         */
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 입장시에는 대화명과 메시지를 자동으로 세팅한다.
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setSender("[알림]");
            message.setMessage(nickname + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }



    /**
     * @MessageMapping 을 통해 WebSocket으로 들어오는 메시지 발행을 처리
     * 클라이언트에서는 Config에서 정의한 prefix를 붙여서 /pub/chat/message로 발행 요청을 하면 Controller가 해당 메시지를 받아 처리
     * <p>
     * messagingTemplate convertAndSend 는 /sub/chat/room/{roomId} 로 메세지를 send함
     * Client에서 /sub/chat/room/{roomId}를 구독하고 있다가 메시지가 전달되면 화면에 출력할 수 있다.
     * 위 경로는 채팅방을 구분하는 값으로 Topic의 역할을 함.
     * @param message
     */




//
//    @PostMapping
//    @ResponseBody
//    public ChatRoom createRoom(@RequestParam String name) {
//        return chatService.createRoom(name);
//    }
//
//    @GetMapping
//    @ResponseBody
//    public List<ChatRoom> findAllRooms() {
//        return chatService.findAllRooms();
//    }
}