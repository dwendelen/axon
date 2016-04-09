package axon.core.infrastructure.mail;

import axon.core.infrastructure.mail.virtual.Mail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VirtualMailServer implements MailClient {
    private List<Mail> mailsById = new ArrayList();

    @Override
    public synchronized void mail(String emailAddress, String message) {
        Mail mail = new Mail(mailsById.size(), emailAddress, message);
        mailsById.add(mail);
    }

    public Mail getMailById(int id) {
        return mailsById.get(id);
    }

    public List<Mail> getMails() {
        return new ArrayList<>(mailsById);
    }
}
