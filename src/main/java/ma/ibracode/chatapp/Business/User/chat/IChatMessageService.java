package ma.ibracode.chatapp.Business.User.chat;

import ma.ibracode.chatapp.Domain.entities.ChatMessage;
import ma.ibracode.chatapp.Infrastructure.ChatMessageRepository;

import java.util.List;

public interface IChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
