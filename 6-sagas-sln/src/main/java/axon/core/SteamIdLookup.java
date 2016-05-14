package axon.core;

import axon.core.game.event.GameRegisteredEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SteamIdLookup {
    private Map<UUID, String> users = new HashMap<>();
    private Map<UUID, String> games = new HashMap<>();

    public Optional<String> getSteamIdForUser(UUID userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public String getSteamIdForGame(UUID gameId) {
        return games.get(gameId);
    }

    @EventHandler
    public void handle(GameRegisteredEvent gameRegisteredEvent) {
        UUID gameId = gameRegisteredEvent.getGameId();
        String steamId = gameRegisteredEvent.getSteamId();

        games.put(gameId, steamId);
    }

    @EventHandler
    public void handle(SteamAccountLinkedEvent steamAccountLinkedEvent) {
        UUID userId = steamAccountLinkedEvent.getUserId();
        String steamId = steamAccountLinkedEvent.getSteamId();

        users.put(userId, steamId);
    }
}
