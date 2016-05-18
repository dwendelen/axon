package axon.core.user.event;

import java.util.UUID;

public class EmailAddressUpdatedEvent implements EmailAddressChangedEvent {
    private UUID userId;
    private String newEmailAddress;

    public EmailAddressUpdatedEvent(UUID userId, String newEmailAddress) {
        this.userId = userId;
        this.newEmailAddress = newEmailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getNewEmailAddress() {
        return newEmailAddress;
    }

    @Override
    public String getEmailAddress() {
        return getNewEmailAddress();
    }
}
