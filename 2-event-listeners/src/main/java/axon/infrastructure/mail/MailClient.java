package axon.infrastructure.mail;

public interface MailClient {
    void mail(String emailAddress, String message);
}
