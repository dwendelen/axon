package axon;

import axon.command.CreateUserCommand;
import axon.event.UserCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID uuid;

    public User() {}

    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        UUID uuid = createUserCommand.getUuid();
        String name = createUserCommand.getName();

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(uuid, name);
        apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void handleCreated(UserCreatedEvent userCreatedEvent) {
        this.uuid = userCreatedEvent.getUuid();
    }

    public UUID getUuid() {
        return uuid;
    }
}
