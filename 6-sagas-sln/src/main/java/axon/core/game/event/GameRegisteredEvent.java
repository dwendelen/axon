package axon.core.game.event;

import java.util.UUID;

public class GameRegisteredEvent {
    private UUID gameId;
    private String steamId;
    private String name;

    public GameRegisteredEvent(UUID gameId, String name, String steamId) {
        this.gameId = gameId;
        this.name = name;
        this.steamId = steamId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getSteamId() {
        return steamId;
    }

    public String getName() {
        return name;
    }
}
