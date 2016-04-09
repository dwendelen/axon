package axon.event;

import java.util.UUID;

public class EmailAddressUpdatedEvent implements EmailAddressChangedEvent {
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

    @Override
    public String getEmailAddress() {
        return getNewEmailAddress();
    }
}
