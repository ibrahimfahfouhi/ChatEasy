package ma.ibracode.chatapp.Api;

import ma.ibracode.chatapp.Business.User.IUserService;
import ma.ibracode.chatapp.Domain.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        _userService = userService;
    }
    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public ResponseEntity<User> addUser(@Payload User user) {
        _userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public ResponseEntity<User> disconnect(@Payload User user) {
        _userService.disconnect(user);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        List<User> result = _userService.findConnectedUsers();
        return ResponseEntity.ok().body(result);
    }
}
