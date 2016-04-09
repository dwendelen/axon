package axon;

import axon.infrastructure.mail.MailClient;
import axon.infrastructure.mail.VirtualMailServer;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

import java.io.File;

public class Application {
    private CommandGateway commandGateway;
    private EventBus eventBus;
    private MailClient mailClient;

    public Application() {
        mailClient = new VirtualMailServer();
    }

    public void init() {
        CommandBus commandBus = new SimpleCommandBus();

        eventBus = new SimpleEventBus();




        EventBus eventBus = new SimpleEventBus();

        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("events")));
        EventSourcingRepository<User> eventSourcedUserRepository = new EventSourcingRepository<>(User.class, eventStore);
        eventSourcedUserRepository.setEventBus(eventBus);

        AggregateAnnotationCommandHandler.subscribe(User.class, eventSourcedUserRepository, commandBus);

        this.commandGateway = new DefaultCommandGateway(commandBus);

        AnnotationEventListenerAdapter.subscribe(new MailSendingEventListener(mailClient), eventBus);
    }

    public <T> T execute(Object command) {
        return commandGateway.sendAndWait(command);
    }

    public void registerListener(Object listener) {
        AnnotationEventListenerAdapter.subscribe(listener, eventBus);
    }

    /**
     * Set before init is called
     */
    public void setMailClient(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    public VirtualMailServer getVirtualMailServer() {
        return (VirtualMailServer)mailClient;
    }
}