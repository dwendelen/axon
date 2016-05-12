package axon.core;

import axon.core.user.event.EmailAddressChangedEvent;
import axon.core.infrastructure.mail.MailClient;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSendingEventListener {
    private MailClient mailClient;

    @Autowired
    public MailSendingEventListener(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    @EventHandler
    public void emailAddressChanged(EmailAddressChangedEvent emailAddressChangedEvent) {
        mailClient.mail(emailAddressChangedEvent.getEmailAddress(), "Welcome!");
    }
}
