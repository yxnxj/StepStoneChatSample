package StepStoneChat.StepStoneChat.domain;

import StepStoneChat.StepStoneChat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 */
@Getter
@Setter
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    private String roomId;
    private String name;
    private long userCount;

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    /**
     * pub/sub 방식을 이용하면 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어진다.
     * 발송의 구현도 알아서 해결되므로 일일이 클라이언트에게 메시지를 발송하는 구현이 필요 없어진다.
     */

//    /**
//     * 채팅방은 입장한 클라이언트의 정보를 가지고 있어야 하기 때문에 WebSocketSession 정보 리스트를 멤버 필드로 갖는다.
//     */
//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    @Builder
//    public ChatRoom(String roomId, String name){
//        this.roomId = roomId;
//        this.name = name;
//    }



//    /**
//     * 채팅방 입장, 대화하기 기능을 handleAction을 통해 분기 처리 한다.
//     *
//     * 입장 시 채팅방의 session 정보를 클라이언트의 session 리스트에 추가해 놓았다가 채팅 방에 메시지가 도착할 경우
//     * 채팅방 안에 있는 모든 session에 메시지를 발송하면 채팅이 완성된다.
//     * @param session
//     * @param chatMessage
//     * @param chatService
//     */
//    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
//        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
//        }
//        sendMessage(chatMessage, chatService);
//    }
//
//    public <T> void sendMessage(T message, ChatService chatService){
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
//    }
}
