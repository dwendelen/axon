package axon.core.game;

import axon.core.game.command.RegisterGameCommand;
import axon.core.game.event.GameRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class Game extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID gameId;

    private String steamId;
    private String name;

    public Game() {}

    @CommandHandler
    public Game(RegisterGameCommand registerGameCommand) {
        GameRegisteredEvent gameRegisteredEvent = new GameRegisteredEvent(
                registerGameCommand.getGameId(),
                registerGameCommand.getName(),
                registerGameCommand.getSteamId()
        );
        apply(gameRegisteredEvent);
    }

    @EventSourcingHandler
    public void handle(GameRegisteredEvent gameRegisteredEvent) {
        this.gameId = gameRegisteredEvent.getGameId();
        this.name = gameRegisteredEvent.getName();
        this.steamId = gameRegisteredEvent.getSteamId();
    }
}
