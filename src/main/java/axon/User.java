package axon;

import axon.command.CreateUserCommand;
import axon.event.UserCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID uuid;

    private String name;

    public User() {}

    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        String name = createUserCommand.name;
        UUID uuid = createUserCommand.uuid;

        if(name == null || uuid == null) {
            throw new IllegalArgumentException();
        }

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        userCreatedEvent.name = name;
        userCreatedEvent.uuid = uuid;

        apply(userCreatedEvent);
    }

    @EventHandler
    public void handleCreated(UserCreatedEvent userCreatedEvent) {
        this.name = userCreatedEvent.name;
        this.uuid = userCreatedEvent.uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }
}
