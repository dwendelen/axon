package axon.hello;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.unitofwork.UnitOfWork;

public class HelloCommandHandler implements CommandHandler<HelloCommand> {
    @Override
    public Object handle(CommandMessage<HelloCommand> commandMessage, UnitOfWork unitOfWork) throws Throwable {
        System.out.print(commandMessage.getPayload().getHello());
        return null;
    }
}
