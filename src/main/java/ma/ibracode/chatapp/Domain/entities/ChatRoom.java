package ma.ibracode.chatapp.Domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ChatRoom {
    @Id
    private String Id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
