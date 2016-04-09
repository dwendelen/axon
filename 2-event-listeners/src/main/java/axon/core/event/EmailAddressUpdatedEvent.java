package axon.core.event;

import java.util.UUID;

public class EmailAddressUpdatedEvent {
    private UUID uuid;
    private String newEmailAddress;

    public EmailAddressUpdatedEvent(UUID uuid, String newEmailAddress) {
        this.uuid = uuid;
        this.newEmailAddress = newEmailAddress;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNewEmailAddress() {
        return newEmailAddress;
    }
}
