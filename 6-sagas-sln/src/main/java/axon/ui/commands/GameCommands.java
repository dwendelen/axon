package axon.ui.commands;

import axon.core.Application;
import axon.core.game.command.RegisterGameCommand;
import axon.ui.game.GameCatalog;
import axon.ui.game.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GameCommands implements CommandMarker {
    @Autowired
    private GameCatalog gameCatalog;
    @Autowired
    private Application application;

    @CliCommand("register-game")
    public String registerGame(
            @CliOption(key = {"name"}, mandatory = true)
                    String name,
            @CliOption(key = {"steamId"}, mandatory = true)
                    String streamId
    ) {
        application.execute(new RegisterGameCommand(name, streamId));
        return "Registered " + name + " with streamId " + streamId;
    }

    @CliCommand("games")
    public String listGames() {
        Collection<GameDTO> games = gameCatalog.getAllGames();

        StringBuilder stringBuilder = new StringBuilder();

        for (GameDTO game : games) {
            stringBuilder.append(game.getName());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
