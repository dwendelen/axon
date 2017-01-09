package axon.core;

import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailAddressCommand;
import axon.core.infrastructure.mail.MailCientMock;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    public static final String NAME = "Name";
    public static final String OLD_EMAIL_ADDRESS = "old email";
    public static final String NEW_EMAIL_ADDRESS = "email";

    private Application application = new Application();
    private MailCientMock mailCientMock = new MailCientMock();

    @Before
    public void setup() {
        application.setMailClient(mailCientMock);
        application.init();
    }

    //1 USER AGGREGATE
    @Test
    public void iCanRegisterAUsersWithANameAndAnEmailAddress() throws Exception {
        RegisterUserCommand command = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
        UUID uuid = command.getUuid();

        application.execute(command);
        User user = application.getUser(uuid);
        assertThat(user.getName()).isEqualTo(NAME);
        assertThat(user.getEmailAddress()).isEqualTo(OLD_EMAIL_ADDRESS);
    }

    @Test
    public void iCanUpdateTheEmailAddress() throws Exception {
        RegisterUserCommand command = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
        UUID uuid = command.getUuid();

        application.execute(command);
        User user = application.getUser(uuid);
        assertThat(user.getEmailAddress()).isEqualTo(OLD_EMAIL_ADDRESS);

        application.execute(new UpdateEmailAddressCommand(uuid, NEW_EMAIL_ADDRESS));
        user = application.getUser(uuid);
        assertThat(user.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
    }

    //2 EVENT LISTENERS
    @Test
    public void whenAUserIsRegistered_thenAnEmailIsSent() throws Exception {
        application.execute(new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS));

        mailCientMock.assertLastEmailAddress(OLD_EMAIL_ADDRESS);
    }

    @Test
    public void whenTheEmailAddressIsUpdated_thenAnEmailIsSent() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
        UUID uuid = registerUserCommand.getUuid();

        application.execute(registerUserCommand);
        application.execute(new UpdateEmailAddressCommand(uuid, NEW_EMAIL_ADDRESS));

        mailCientMock.assertLastEmailAddress(NEW_EMAIL_ADDRESS);
    }
}