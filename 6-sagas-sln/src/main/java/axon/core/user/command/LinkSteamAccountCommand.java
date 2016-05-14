package axon.core.user.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class LinkSteamAccountCommand {
    @TargetAggregateIdentifier
    private UUID userId;
    private String steamId;

    public LinkSteamAccountCommand(UUID userId, String steamId) {
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
