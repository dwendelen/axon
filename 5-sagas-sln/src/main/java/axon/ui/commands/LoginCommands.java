package axon.ui.commands;

import axon.ui.Context;
import axon.ui.user.UserDTO;
import axon.ui.user.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class LoginCommands implements CommandMarker {
    @Autowired
    private Context context;
    @Autowired
    private UserDTORepository userDTORepository;

    @CliAvailabilityIndicator({"login"})
    public boolean canLogin() {
        return !context.isLoggedIn();
    }

    @CliAvailabilityIndicator({"logout"})
    public boolean canLogout() {
        return context.isLoggedIn();
    }

    @CliCommand("login")
    public String login(
            @CliOption(key = {""})
            String name
    ) {
        if (name == null) {
            return showNames();
        } else {
            return doLogin(name);
        }
    }

    private String showNames() {
        StringBuilder stringBuilder = new StringBuilder();
        userDTORepository.getAllUsers()
                .stream()
                .forEach(user -> {
                    stringBuilder.append(user.getName());
                    stringBuilder.append("\n");
                });
        return stringBuilder.toString();
    }

    private String doLogin(String name) {
        UserDTO user = userDTORepository.getUser(name);
        context.loggedIn(user);
        return "Welcome " + name;
    }

    @CliCommand("logout")
    public String logout() {
        context.logout();
        return "Logged out";
    }
}
