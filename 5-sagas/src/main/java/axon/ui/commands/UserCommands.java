package axon.ui.commands;

import axon.core.Application;
import axon.core.user.command.RegisterUserCommand;
import axon.core.user.command.UpdateEmailAddressCommand;
import axon.core.user.exception.GameAlreadyBoughtException;
import axon.ui.Context;
import axon.ui.game.GameCatalog;
import axon.ui.game.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserCommands implements CommandMarker {
    @Autowired
    private Application application;
    @Autowired
    private Context context;
    @Autowired
    private GameCatalog gameCatalog;

    @CliAvailabilityIndicator("update-email")
    public boolean canUpdateEmail() {
        return context.isLoggedIn();
    }

    @CliCommand("register")
    public String register(
            @CliOption(key = {"name"}, mandatory = true)
            String name,
            @CliOption(key = {"email"}, mandatory = true)
            String emailAddress
    ) {
        application.execute(new RegisterUserCommand(name, emailAddress));
        return "Registered " + name + " with email address " + emailAddress;
    }

    @CliCommand("update-email")
    public String updateEmail(
            @CliOption(key = {""}, mandatory = true)
            String emailAddress
    ) {
        application.execute(new UpdateEmailAddressCommand(context.getCurrentUUID(), emailAddress));
        return "Updated email address to " + emailAddress;
    }

    @CliCommand("link-steam")
    public String linkSteamAccount(
        @CliOption(key = "", mandatory = true)
        String steamUserId
    ) {
        UUID userId = context.getCurrentUUID();

        application.execute(null); //TODO
        return "Steam id " + steamUserId + " linked";
    }

    @CliCommand("buy-game")
    public String buyGame(
            @CliOption(key = "", mandatory = true)
            String gameName
    ) {
        GameDTO gameDTO = gameCatalog.getGame(gameName);

        UUID gameId = gameDTO.getGameId();
        UUID userId = context.getCurrentUUID();

        try {
            application.execute(null); //TODO
            return "You bought " + gameName;
        } catch (GameAlreadyBoughtException e) {
            return "Could not buy " + gameName + " because you already bought it";
        }
    }
}
