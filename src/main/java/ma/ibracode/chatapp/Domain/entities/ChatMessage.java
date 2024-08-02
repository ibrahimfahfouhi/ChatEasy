package ma.ibracode.chatapp.Domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalDate timeStamp;
}
