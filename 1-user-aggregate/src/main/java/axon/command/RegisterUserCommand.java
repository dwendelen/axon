package axon.command;

import java.util.UUID;

public class RegisterUserCommand {
    private UUID uuid = UUID.randomUUID();

    private String name;

    public RegisterUserCommand(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
