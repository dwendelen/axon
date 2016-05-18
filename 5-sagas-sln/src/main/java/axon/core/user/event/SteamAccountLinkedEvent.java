package axon.core.user.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class SteamAccountLinkedEvent {
    @TargetAggregateIdentifier
    private UUID userId;
    private String steamId;

    public SteamAccountLinkedEvent(UUID userId, String steamId) {
        this.userId = userId;
        this.steamId = steamId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getSteamId() {
        return steamId;
    }
}
