package ma.ibracode.chatapp.Infrastructure;

import ma.ibracode.chatapp.Domain.entities.Client;
import ma.ibracode.chatapp.Domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Client, String> {
    List<Client> findAllByStatus(Status status);
}
