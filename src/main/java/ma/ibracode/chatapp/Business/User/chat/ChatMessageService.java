package ma.ibracode.chatapp.Business.User.chat;

import ma.ibracode.chatapp.Business.User.chatRoom.IChatRoomService;
import ma.ibracode.chatapp.Domain.entities.ChatMessage;
import ma.ibracode.chatapp.Infrastructure.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService implements IChatMessageService{
    private final ChatMessageRepository _chatMessageRepository;
    private final IChatRoomService _chatRoomService;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, IChatRoomService chatRoomService) {
        _chatMessageRepository = chatMessageRepository;
        _chatRoomService = chatRoomService;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = _chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        _chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = _chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(_chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
