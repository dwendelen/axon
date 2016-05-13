package axon.core;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SteamIdLookup {
    public Optional<String> getSteamIdForUser(UUID userId) {
        throw new UnsupportedOperationException();
    }
}
