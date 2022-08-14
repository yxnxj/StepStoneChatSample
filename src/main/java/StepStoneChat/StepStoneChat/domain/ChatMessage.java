package StepStoneChat.StepStoneChat.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
