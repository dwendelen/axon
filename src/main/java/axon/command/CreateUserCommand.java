package axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateUserCommand {
    @TargetAggregateIdentifier
    public UUID uuid = UUID.randomUUID();
    public String name;
}
