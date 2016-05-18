package axon.core;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {
    @Autowired
    private CommandGateway commandGateway;

    public <T> T execute(Object command) {
        return commandGateway.sendAndWait(command);
    }
}