package axon.core.user.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class UpdateEmailAddressCommand {
    @TargetAggregateIdentifier
    private UUID userId;

    private String newEmailAddress;

    public UpdateEmailAddressCommand(UUID userId, String newEmailAddress) {
        this.userId = userId;
        this.newEmailAddress = newEmailAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getNewEmailAddress() {
        return newEmailAddress;
    }
}
