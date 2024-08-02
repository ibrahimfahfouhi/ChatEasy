package ma.ibracode.chatapp.Business.User;

import ma.ibracode.chatapp.Domain.entities.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);
    void disconnect(User user);
    List<User> findConnectedUsers();
}
