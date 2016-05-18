package axon.ui.commands;

import axon.core.infrastructure.logger.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class LoggingCommands implements CommandMarker {
    @Autowired
    private SystemOutLogger systemOutLogger;

    @CliCommand("log on")
    public void logon() {
        systemOutLogger.logOn();
    }

    @CliCommand("log off")
    public void logoff() {
        systemOutLogger.logOff();
    }
}
