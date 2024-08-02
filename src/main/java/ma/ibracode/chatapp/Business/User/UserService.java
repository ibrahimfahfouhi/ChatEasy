package ma.ibracode.chatapp.Business.User;

import ma.ibracode.chatapp.Domain.entities.User;
import ma.ibracode.chatapp.Domain.enums.Status;
import ma.ibracode.chatapp.Infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService{
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        _userRepository.save(user);
    }

    @Override
    public void disconnect(User user) {
        var storedUser = _userRepository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            _userRepository.save(storedUser);
        }
    }

    @Override
    public List<User> findConnectedUsers() {
        return _userRepository.findAllByStatus(Status.ONLINE);
    }
}
