package axon.command;

import java.util.UUID;

public class CreateUserCommand {
    private UUID uuid = UUID.randomUUID();

    private String name;

    public CreateUserCommand(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
