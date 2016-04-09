package axon.infrastructure.mail;

public class SystemOutLoggingMailClient implements MailClient {
    @Override
    public void mail(String emailAddress, String message) {
        System.out.println("####################");
        System.out.println("To: " + emailAddress);
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println("####################");
    }
}
