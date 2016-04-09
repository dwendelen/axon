package axon.ui.commands;

import axon.core.Application;
import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailAddressCommand;
import axon.ui.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class UserCommands implements CommandMarker {
    @Autowired
    private Application application;
    @Autowired
    private Context context;

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
}
