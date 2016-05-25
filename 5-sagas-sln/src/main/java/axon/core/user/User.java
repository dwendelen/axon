package axon.core.user;

import axon.core.user.command.BuyGameCommand;
import axon.core.user.command.RegisterUserCommand;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID userId;

    public User() {}

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        UUID userId = registerUserCommand.getUserId();
        String name = registerUserCommand.getName();
        String emailAddress = registerUserCommand.getEmailAddress();

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(userId, name, emailAddress);
        apply(userRegisteredEvent);
    }


    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.userId = userRegisteredEvent.getUserId();
    }

    @CommandHandler
    public void buyGame(BuyGameCommand buyGameCommand) {
        GameBoughtEvent gameBoughtEvent = new GameBoughtEvent(
                buyGameCommand.getUserId(),
                buyGameCommand.getGameId(),
                buyGameCommand.getPurchaseId());
        try {
            apply(gameBoughtEvent);
        } catch (Exception e){}
    }

    @EventSourcingHandler
    public void handle(GameBoughtEvent gameBoughtEvent) {
        throw new RuntimeException("Any RuntimeException will do");
    }
}
