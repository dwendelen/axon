package axon;

import axon.command.RegisterUserCommand;
import axon.command.UpdateEmailAddressCommand;
import axon.event.EmailAddressUpdatedEvent;
import axon.event.UserRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID uuid;

    private String name;
    private String emailAddress;

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

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.uuid = userRegisteredEvent.getUuid();
        this.name = userRegisteredEvent.getName();
        this.emailAddress = userRegisteredEvent.getEmailAddress();
    }

    @CommandHandler
    public void updateEmailAddress(UpdateEmailAddressCommand updateEmailAddressCommand) {
        UUID uuid = updateEmailAddressCommand.getUuid();
        String newEmailAddress = updateEmailAddressCommand.getNewEmailAddress();

        Assert.notEmpty(newEmailAddress, "The new email address can not be empty");

        EmailAddressUpdatedEvent emailAddressUpdatedEvent = new EmailAddressUpdatedEvent(uuid, newEmailAddress);
        apply(emailAddressUpdatedEvent);
    }

    @EventSourcingHandler
    public void emailAddressUpdated(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
        this.emailAddress = emailAddressUpdatedEvent.getNewEmailAddress();
    }
}
