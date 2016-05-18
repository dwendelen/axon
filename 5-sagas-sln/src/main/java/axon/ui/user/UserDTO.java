package axon.ui.user;

import java.util.UUID;

public class UserDTO {
    private UUID userId;
    private String name;

    public UserDTO(UUID userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getUserId() {
        return userId;
    }
}