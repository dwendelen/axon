package axon.ui.user;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String name;

    public UserDTO(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }
}