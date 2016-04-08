package axon.command;

import java.util.UUID;

public class RegisterUserCommand {
    private UUID uuid = UUID.randomUUID();

    private String name;
    private String emailAddress;

    public RegisterUserCommand(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
