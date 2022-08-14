package StepStoneChat.StepStoneChat.controller;

import StepStoneChat.StepStoneChat.domain.Message;
import StepStoneChat.StepStoneChat.domain.OutputMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {
    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/messages/{chatId}")
    public OutputMessage send(Message message, @DestinationVariable String chatId){
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

}
