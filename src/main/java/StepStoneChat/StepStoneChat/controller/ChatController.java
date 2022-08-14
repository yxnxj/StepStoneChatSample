package StepStoneChat.StepStoneChat.controller;

import StepStoneChat.StepStoneChat.domain.ChatRoom;
import StepStoneChat.StepStoneChat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    @ResponseBody
    public List<ChatRoom> findAllRooms() {
        return chatService.findAllRooms();
    }
}