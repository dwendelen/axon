package axon.ui.user;

import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {
    @Autowired
    private UserDTORepository userDTORepository;

    @EventHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        UserDTO userDTO = new UserDTO(
                userRegisteredEvent.getUserId(),
                userRegisteredEvent.getName());

        userDTORepository.add(userDTO);
    }
}