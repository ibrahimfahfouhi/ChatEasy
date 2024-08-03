package ma.ibracode.chatapp.Domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import ma.ibracode.chatapp.Domain.enums.Status;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
