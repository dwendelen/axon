package axon.core.user;

import axon.core.MailSendingEventListener;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.UserRegisteredEvent;
import axon.core.infrastructure.mail.MailCientMock;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class MailSendingEventListenerTest {
    public static final String EMAIL_ADDRESS = "email";
    public static final UUID USER_UUID = UUID.randomUUID();
    public static final String NAME = "name";

    private MailSendingEventListener mailSendingEventListener;
    private MailCientMock mailCientMock = new MailCientMock();

    @Before
    public void setup() {
        mailSendingEventListener = new MailSendingEventListener(mailCientMock);
    }

    //3 READ MODELS
    @Test
    public void whenAUserIsRegistered_thenAnEmailIsSent() throws Exception {
        UserRegisteredEvent emailAddressChangedEvent = new UserRegisteredEvent(USER_UUID, NAME, EMAIL_ADDRESS);
        mailSendingEventListener.emailAddressChanged(emailAddressChangedEvent);

        mailCientMock.assertLastEmailAddress(EMAIL_ADDRESS);
    }

    @Test
    public void whenTheEmailAddressIsUpdated_thenAnEmailIsSent() throws Exception {
        EmailAddressUpdatedEvent emailAddressChangedEvent = new EmailAddressUpdatedEvent(USER_UUID, EMAIL_ADDRESS);
        mailSendingEventListener.emailAddressChanged(emailAddressChangedEvent);

        mailCientMock.assertLastEmailAddress(EMAIL_ADDRESS);
    }

    //4 SPRING
    //5 SAGAS
    //6 TODO
}
