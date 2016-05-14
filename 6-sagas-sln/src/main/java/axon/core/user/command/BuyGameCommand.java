package axon.core.user.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class BuyGameCommand {
    @TargetAggregateIdentifier
    private UUID userId;
    private UUID gameId;
    private UUID purchaseId = UUID.randomUUID();

    public BuyGameCommand(UUID userId, UUID gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }
}
