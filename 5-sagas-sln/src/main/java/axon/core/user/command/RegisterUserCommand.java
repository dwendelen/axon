package axon.core.user.command;

import java.util.UUID;

public class RegisterUserCommand {
    private UUID userId = UUID.randomUUID();

    private String name;
    private String emailAddress;

    public RegisterUserCommand(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
