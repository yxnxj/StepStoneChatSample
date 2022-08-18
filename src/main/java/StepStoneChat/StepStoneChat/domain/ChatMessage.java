package StepStoneChat.StepStoneChat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private long userCount;
    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }
}
