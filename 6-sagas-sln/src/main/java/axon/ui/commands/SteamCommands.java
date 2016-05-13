package axon.ui.commands;

import axon.core.infrastructure.steam.VirtualSteamServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class SteamCommands implements CommandMarker {
    @Autowired
    private VirtualSteamServer steamServer;

    @CliCommand("steam list")
    public String listSteamRegisterations() {
        return steamServer.getLogsToPrint();
    }
}
