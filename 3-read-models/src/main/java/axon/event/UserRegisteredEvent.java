package axon.event;

import java.util.UUID;

public class UserRegisteredEvent implements EmailAddressChangedEvent {
    private UUID uuid;

    private String name;
    private String emailAddress;

    public UserRegisteredEvent(UUID uuid, String name, String emailAddress) {
        this.uuid = uuid;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }
}
