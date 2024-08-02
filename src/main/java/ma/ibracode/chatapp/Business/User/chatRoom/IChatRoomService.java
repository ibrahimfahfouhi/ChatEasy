package ma.ibracode.chatapp.Business.User.chatRoom;

import java.util.Optional;

public interface IChatRoomService {
    Optional<String> getChatRoomId(String senderId, String recipientId, Boolean createNewRoomIfNotExist);

}
