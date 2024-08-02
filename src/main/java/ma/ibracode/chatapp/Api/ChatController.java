package ma.ibracode.chatapp.Api;

import ma.ibracode.chatapp.Business.User.chat.ChatNotification;
import ma.ibracode.chatapp.Business.User.chat.IChatMessageService;
import ma.ibracode.chatapp.Domain.entities.ChatMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final SimpMessagingTemplate _simpMessagingTemplate;
    private final IChatMessageService _chatMessageService;
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, IChatMessageService chatMessageService) {
        _simpMessagingTemplate = simpMessagingTemplate;
        _chatMessageService = chatMessageService;
    }
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMessage = _chatMessageService.save(chatMessage);
        _simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),
                "/queue/message",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .senderId(savedMessage.getSenderId())
                        .recipientId(savedMessage.getRecipientId())
                        .content(savedMessage.getContent())
                        .build()
        );

    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        List<ChatMessage> result = _chatMessageService.findChatMessages(senderId, recipientId);
        return ResponseEntity.ok().body(result);
    }
}
