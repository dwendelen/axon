package axon;

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
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;

import java.io.File;
import java.util.UUID;

public class Application {
    private CommandGateway commandGateway;
    private Repository<User> userRepository;

    public void init() {
        CommandBus commandBus = new SimpleCommandBus();

        EventBus eventBus = new SimpleEventBus();

        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("events")));
        EventSourcingRepository<User> eventSourcedUserRepository = new EventSourcingRepository<>(User.class, eventStore);
        eventSourcedUserRepository.setEventBus(eventBus);

        AggregateAnnotationCommandHandler.subscribe(User.class, eventSourcedUserRepository, commandBus);

        this.commandGateway = new DefaultCommandGateway(commandBus);
        this.userRepository = eventSourcedUserRepository;
    }

    public <T> T send(Object command) {
        return commandGateway.sendAndWait(command);
    }

    public User getUser(UUID uuid) {
        UnitOfWork unitOfWork = DefaultUnitOfWork.startAndGet();
        User user = userRepository.load(uuid);
        unitOfWork.commit();
        return user;
    }
}
