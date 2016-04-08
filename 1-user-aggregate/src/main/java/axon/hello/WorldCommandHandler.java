package axon.hello;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class WorldCommandHandler {
    @CommandHandler
    public void world(WorldCommand worldCommand) {
        System.out.println(worldCommand.getWorld());
    }
}
