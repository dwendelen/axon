package axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class UpdateEmailAddressCommand {
    @TargetAggregateIdentifier
    private UUID uuid;

    private String newEmailAddress;

    public UpdateEmailAddressCommand(UUID uuid, String newEmailAddress) {
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
