package axon.ui.game;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class GameCatalog {
    private Map<String, GameDTO> games = new HashMap<>();

    public Collection<GameDTO> getAllGames() {
        return games.values();
    }

    public GameDTO getGame(String gameName) {
        return games.get(gameName);
    }

    public void addGame(GameDTO gameDTO) {
        games.put(gameDTO.getName(), gameDTO);
    }
}
