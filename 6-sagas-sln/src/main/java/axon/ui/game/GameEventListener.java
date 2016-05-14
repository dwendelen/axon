package axon.ui.game;

import axon.core.game.event.GameRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameEventListener {
    @Autowired
    private GameCatalog gameCatalog;

    @EventHandler
    public void gameRegistered(GameRegisteredEvent gameRegisteredEvent) {
        GameDTO gameDTO = new GameDTO(gameRegisteredEvent.getGameId(), gameRegisteredEvent.getName());
        gameCatalog.addGame(gameDTO);
    }
}
