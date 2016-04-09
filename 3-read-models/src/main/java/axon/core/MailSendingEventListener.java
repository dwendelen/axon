package axon.core;

import axon.core.event.EmailAddressChangedEvent;
import axon.core.infrastructure.mail.MailClient;
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
