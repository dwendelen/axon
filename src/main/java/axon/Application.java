package axon;

import axon.command.CreateUserCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

import java.io.File;

public class Application {

    public static void main(String args[]) {
        CommandBus commandBus = new SimpleCommandBus();

        EventBus eventBus = new SimpleEventBus();

        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("events")));
        EventSourcingRepository<User> eventSourcingRepository = new EventSourcingRepository<>(User.class, eventStore);
        eventSourcingRepository.setEventBus(eventBus);

        AggregateAnnotationCommandHandler.subscribe(User.class, eventSourcingRepository, commandBus);

        CommandGateway commandGateway = new DefaultCommandGateway(commandBus);



        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.name = "name";
        commandGateway.send(createUserCommand);

        User user = eventSourcingRepository.load(createUserCommand.uuid);
        System.out.println(user.getUuid());
        System.out.println(user.getName());
    }
}
