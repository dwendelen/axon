package axon.core.game.command;

import java.util.UUID;

public class RegisterGameCommand {
    private UUID gameId = UUID.randomUUID();
    private String steamId;
    private String name;

    public RegisterGameCommand(String name, String steamId) {
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
