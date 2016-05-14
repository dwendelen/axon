package axon.core.infrastructure.steam;

import org.springframework.shell.support.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class VirtualSteamServer implements SteamGateway {
    private String logsToPrint = "";

    public VirtualSteamServer() {

    }

    @Override
    public void registerGame(String steamUserId, String steamGameId) {
        String extraLine = StringUtils.padRight(steamUserId, 16) + steamGameId + "\n";
        logsToPrint += extraLine;
    }

    public String getLogsToPrint() {
        return logsToPrint;
    }
}
