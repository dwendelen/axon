package axon.core.user.event;

import java.util.UUID;

public class UserRegisteredEvent implements EmailAddressChangedEvent {
    private UUID userId;

    private String name;
    private String emailAddress;

    public UserRegisteredEvent(UUID userId, String name, String emailAddress) {
        this.userId = userId;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }
}
