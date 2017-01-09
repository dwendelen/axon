package axon.core.user;

import axon.core.Application;
import axon.core.user.command.RegisterUserCommand;
import axon.core.user.command.UpdateEmailAddressCommand;
import axon.core.infrastructure.mail.MailCientMock;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-application-test.xml")
public class ApplicationTest {
    public static final String NAME = "Name";
    public static final String OLD_EMAIL_ADDRESS = "old email";
    public static final String NEW_EMAIL_ADDRESS = "email";

    @Autowired
    private Application application;
    @Autowired
    private MailCientMock mailCientMock;

    //1 USER AGGREGATE
    @Test
    @Ignore
    public void iCanRegisterAUsersWithANameAndAnEmailAddress() throws Exception {
//        RegisterUserCommand command = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
//        UUID uuid = command.getUuid();
//
//        application.execute(command);
//        User user = application.getUser(uuid);
//        assertThat(user.getName()).isEqualTo(NAME);
//        assertThat(user.getEmailAddress()).isEqualTo(OLD_EMAIL_ADDRESS);
    }

    @Test
    @Ignore
    public void iCanUpdateTheEmailAddress() throws Exception {
//        RegisterUserCommand command = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
//        UUID uuid = command.getUuid();
//
//        application.execute(command);
//        User user = application.getUser(uuid);
//        assertThat(user.getEmailAddress()).isEqualTo(OLD_EMAIL_ADDRESS);
//
//        application.execute(new UpdateEmailAddressCommand(uuid, NEW_EMAIL_ADDRESS));
//        user = application.getUser(uuid);
//        assertThat(user.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
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
        UUID uuid = registerUserCommand.getUserId();

        application.execute(registerUserCommand);
        application.execute(new UpdateEmailAddressCommand(uuid, NEW_EMAIL_ADDRESS));

        mailCientMock.assertLastEmailAddress(NEW_EMAIL_ADDRESS);
    }

    //3 READ MODELS
    //4 SPRING
    //5 SAGAS
}