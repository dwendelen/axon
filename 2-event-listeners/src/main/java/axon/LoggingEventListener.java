package axon;

import axon.event.UserRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;

public class LoggingEventListener {
    @EventHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        System.out.println(userRegisteredEvent.getName() + " registered");
    }
}
