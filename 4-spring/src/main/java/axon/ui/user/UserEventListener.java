package axon.ui.user;

import axon.core.Application;
import axon.core.event.UserRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserEventListener {
    @Autowired
    private Application application;
    @Autowired
    private UserDTORepository userDTORepository;

    @PostConstruct
    public void register() {
        application.registerListener(this);
    }

    @EventHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        UserDTO userDTO = new UserDTO(
                userRegisteredEvent.getUuid(),
                userRegisteredEvent.getName());

        userDTORepository.add(userDTO);
    }
}