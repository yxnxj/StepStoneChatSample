package StepStoneChat.StepStoneChat.controller;

import StepStoneChat.StepStoneChat.domain.ChatMessage;
import StepStoneChat.StepStoneChat.domain.ChatRoom;
import StepStoneChat.StepStoneChat.repository.ChatRoomRepository;
import StepStoneChat.StepStoneChat.service.ChatService;
import StepStoneChat.StepStoneChat.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/chats")
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
//    @MessageMapping("/chat/message")
//    public void message(ChatMessage message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            chatRoomRepository.enterChatRoom(message.getRoomId());
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        }
//        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
//    }

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