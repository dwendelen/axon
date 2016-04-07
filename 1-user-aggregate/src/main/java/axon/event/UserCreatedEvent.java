package axon.event;

import java.util.UUID;

public class UserCreatedEvent {
    private UUID uuid;
    private String name;

    public UserCreatedEvent(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
