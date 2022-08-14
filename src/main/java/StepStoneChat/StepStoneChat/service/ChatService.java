package StepStoneChat.StepStoneChat.service;

import StepStoneChat.StepStoneChat.domain.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    /**
     * 서버에 생성된 모든 채팅방의 정보를 모아둔 Map
     * 추후 Redis DB에 저장하는 방식으로 수정할 예정
     *
     * 채팅방 조회 - 채팅방 Map에 담긴 정보 조회
     * 채팅방 생성 - Random UUID로 구별 ID를 가진 채팅방 객체를 생성하고 채팅방 Map 추가
     * 메시지 발송 - 지정한 WebSocket세션에 메시지를 발송
     */
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct // WAS 가 띄워질 때 자동 실행
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch(IOException e){
            log.error(e.getMessage(), e);
        }
    }
}
