package ma.ibracode.chatapp.Business.User.chatRoom;

import ma.ibracode.chatapp.Domain.entities.ChatRoom;
import ma.ibracode.chatapp.Infrastructure.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService implements IChatRoomService{
    private final ChatRoomRepository _chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        _chatRoomRepository = chatRoomRepository;
    }

    @Override
    public Optional<String> getChatRoomId(String senderId, String recipientId, Boolean createNewRoomIfNotExist) {
        return _chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExist) {
                        var chatId = createChat(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChat(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        ChatRoom recipientsender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        _chatRoomRepository.save(senderRecipient);
        _chatRoomRepository.save(recipientsender);
        return chatId;
    }
}
