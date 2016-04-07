package axon.hello;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class HelloCommandHandler {
    @CommandHandler
    public void hello(HelloCommand helloCommand) {
        System.out.println("Hello " + helloCommand.getHello());
    }
}
