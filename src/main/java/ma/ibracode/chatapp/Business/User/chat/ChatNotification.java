package ma.ibracode.chatapp.Business.User.chat;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ChatNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
