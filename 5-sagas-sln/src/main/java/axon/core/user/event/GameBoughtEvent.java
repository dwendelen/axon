package axon.core.user.event;

import java.util.UUID;

public class GameBoughtEvent {
    private UUID userId;
    private UUID gameId;
    private UUID purchaseId;

    public GameBoughtEvent(UUID userId, UUID gameId, UUID purchaseId) {
        this.userId = userId;
        this.gameId = gameId;
        this.purchaseId = purchaseId;
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
