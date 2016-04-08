package axon;

import axon.command.RegisterUserCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class ApplicationTest {
    public static final String NAME = "Name";
    public static final String OLD_EMAIL_ADDRESS = "old email";
    public static final String NEW_EMAIL_ADDRESS = "email";

    private Application application = new Application();

    @Before
    public void setup() {
        application.init();
    }

    @Test
    public void iCanRegisterAUsersWithANameAndAnEmailAddress() throws Exception {
        RegisterUserCommand command = new RegisterUserCommand(NAME); //TODO
        UUID uuid = command.getUuid();

        application.send(command);
        User user = application.getUser(uuid);
        assertThat(user.getName()).isEqualTo(NAME);
        assertThat(user.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
    }

    @Test
    public void iCanChangeTheEmailAddress() throws Exception {
        RegisterUserCommand command = new RegisterUserCommand(NAME); //TODO
        UUID uuid = command.getUuid();

        application.send(command);
        User user = application.getUser(uuid);
        assertThat(user.getEmailAddress()).isEqualTo(OLD_EMAIL_ADDRESS);

        application.send(null); //TODO
        user = application.getUser(uuid);
        assertThat(user.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
    }
}