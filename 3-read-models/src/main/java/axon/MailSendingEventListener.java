package axon;

import axon.event.EmailAddressChangedEvent;
import axon.infrastructure.mail.MailClient;
import org.axonframework.eventhandling.annotation.EventHandler;

public class MailSendingEventListener {
    private MailClient mailClient;

    public MailSendingEventListener(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    @EventHandler
    public void emailAddressChanged(EmailAddressChangedEvent emailAddressChangedEvent) {
        mailClient.mail(emailAddressChangedEvent.getEmailAddress(), "Welcome!");
    }
}
