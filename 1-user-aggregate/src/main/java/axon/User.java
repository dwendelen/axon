package axon;

import axon.command.RegisterUserCommand;
import axon.event.UserRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID uuid;

    public User() {}

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        UUID uuid = registerUserCommand.getUuid();

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(uuid);
        apply(userRegisteredEvent);
    }

    @EventSourcingHandler
    public void handleRegistaration(UserRegisteredEvent userRegisteredEvent) {
        this.uuid = userRegisteredEvent.getUuid();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getEmailAddress() {
        throw new UnsupportedOperationException();
    }
}
