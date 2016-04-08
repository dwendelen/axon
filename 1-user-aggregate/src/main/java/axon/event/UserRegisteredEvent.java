package axon.event;

import java.util.UUID;

public class UserRegisteredEvent {
    private UUID uuid;

    public UserRegisteredEvent(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }
}
