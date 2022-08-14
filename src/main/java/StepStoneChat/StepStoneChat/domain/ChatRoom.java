package StepStoneChat.StepStoneChat.domain;

import StepStoneChat.StepStoneChat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    /**
     * 채팅방은 입장한 클라이언트의 정보를 가지고 있어야 하기 때문에 WebSocketSession 정보 리스트를 멤버 필드로 갖는다.
     */
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }

    /**
     * 채팅방 입장, 대화하기 기능을 handleAction을 통해 분기 처리 한다.
     *
     * 입장 시 채팅방의 session 정보를 클라이언트의 session 리스트에 추가해 놓았다가 채팅 방에 메시지가 도착할 경우
     * 채팅방 안에 있는 모든 session에 메시지를 발송하면 채팅이 완성된다.
     * @param session
     * @param chatMessage
     * @param chatService
     */
    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
