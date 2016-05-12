package axon.ui.game;

import java.util.UUID;

public class GameDTO {
    private UUID gameId;
    private String name;

    public GameDTO(UUID gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getGameId() {
        return gameId;
    }
}
