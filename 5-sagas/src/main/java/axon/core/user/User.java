package axon.core.user;

import axon.core.user.command.RegisterUserCommand;
import axon.core.user.command.UpdateEmailAddressCommand;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID userId;

    private String name;
    private String emailAddress;

    public User() {}

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        UUID userId = registerUserCommand.getUserId();
        String name = registerUserCommand.getName();
        String emailAddress = registerUserCommand.getEmailAddress();

        Assert.notNull(userId, "UserId can not be null");
        Assert.notEmpty(name, "Name can not be empty");
        Assert.notEmpty(emailAddress, "Email address can not be empty");

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(userId, name, emailAddress);
        apply(userRegisteredEvent);
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.userId = userRegisteredEvent.getUserId();
        this.name = userRegisteredEvent.getName();
        this.emailAddress = userRegisteredEvent.getEmailAddress();
    }

    @CommandHandler
    public void updateEmailAddress(UpdateEmailAddressCommand updateEmailAddressCommand) {
        UUID userId = updateEmailAddressCommand.getUserId();
        String newEmailAddress = updateEmailAddressCommand.getNewEmailAddress();

        Assert.notEmpty(newEmailAddress, "The new email address can not be empty");

        EmailAddressUpdatedEvent emailAddressUpdatedEvent = new EmailAddressUpdatedEvent(userId, newEmailAddress);
        apply(emailAddressUpdatedEvent);
    }

    @EventSourcingHandler
    public void emailAddressUpdated(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
        this.emailAddress = emailAddressUpdatedEvent.getNewEmailAddress();
    }

    //TODO Buy games
    //TODO Link Steam account
}
