package axon.core;

import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailAddressCommand;
import axon.core.event.EmailAddressUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.common.Assert;
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
        String name = registerUserCommand.getName();
        String emailAddress = registerUserCommand.getEmailAddress();

        Assert.notNull(uuid, "UUID can not be null");
        Assert.notEmpty(name, "Name can not be empty");
        Assert.notEmpty(emailAddress, "Email address can not be empty");

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(uuid, name, emailAddress);
        apply(userRegisteredEvent);
    }

    public UUID getUuid() {
        return uuid;
    }

    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.uuid = userRegisteredEvent.getUuid();
    }

    @CommandHandler
    public void updateEmailAddress(UpdateEmailAddressCommand updateEmailAddressCommand) {
        UUID uuid = updateEmailAddressCommand.getUuid();
        String newEmailAddress = updateEmailAddressCommand.getNewEmailAddress();

        Assert.notEmpty(newEmailAddress, "The new email address can not be empty");

        EmailAddressUpdatedEvent emailAddressUpdatedEvent = new EmailAddressUpdatedEvent(uuid, newEmailAddress);
        apply(emailAddressUpdatedEvent);
    }
}
