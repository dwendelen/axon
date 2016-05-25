package axon.core.steam;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SteamIdLookup {
    public Optional<String> getSteamAccountIdForUser(UUID userId) {
        throw new UnsupportedOperationException();
    }

    public String getSteamGameIdForGame(UUID gameId) {
        throw new UnsupportedOperationException();
    }

    //TODO Implement and Complete
}
