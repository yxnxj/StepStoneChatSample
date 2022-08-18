package StepStoneChat.StepStoneChat.service;

import StepStoneChat.StepStoneChat.domain.ChatMessage;
import StepStoneChat.StepStoneChat.domain.ChatRoom;
import StepStoneChat.StepStoneChat.repository.ChatRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatRoomRepository chatRoomRepository;

    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    /**
     * 채팅방에 메시지 발송
     */
    public void sendChatMessage(ChatMessage chatMessage) {
        chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에 입장했습니다.");
            chatMessage.setSender("[알림]");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }


    /**
     * Repository로 migration
     */

//    private final ObjectMapper objectMapper;
//    /**
//     * 서버에 생성된 모든 채팅방의 정보를 모아둔 Map
//     * 추후 Redis DB에 저장하는 방식으로 수정할 예정
//     *
//     * 채팅방 조회 - 채팅방 Map에 담긴 정보 조회
//     * 채팅방 생성 - Random UUID로 구별 ID를 가진 채팅방 객체를 생성하고 채팅방 Map 추가
//     * 메시지 발송 - 지정한 WebSocket세션에 메시지를 발송
//     */
//    private Map<String, ChatRoom> chatRooms;
//
//    @PostConstruct // WAS 가 띄워질 때 자동 실행
//    private void init(){
//        chatRooms = new LinkedHashMap<>();
//    }
//
//    public List<ChatRoom> findAllRooms(){
//        return new ArrayList<>(chatRooms.values());
//    }
//
//    public ChatRoom findRoomById(String roomId){
//        return chatRooms.get(roomId);
//    }
//
////    public ChatRoom createRoom(String name){
//////        String randomId = UUID.randomUUID().toString();
//////        ChatRoom chatRoom = ChatRoom.builder()
//////                .roomId(randomId)
//////                .name(name)
//////                .build();
//////        chatRooms.put(randomId, chatRoom);
//////        return chatRoom;
////    }
//
//    public <T> void sendMessage(WebSocketSession session, T message){
//        try{
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch(IOException e){
//            log.error(e.getMessage(), e);
//        }
//    }
}
