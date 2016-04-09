package axon.infrastructure.mail;

import axon.infrastructure.mail.virtual.Mail;

import java.util.ArrayList;
import java.util.List;

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
