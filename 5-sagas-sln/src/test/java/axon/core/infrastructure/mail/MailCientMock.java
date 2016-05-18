package axon.core.infrastructure.mail;

import static org.assertj.core.api.Assertions.assertThat;

public class MailCientMock implements MailClient {
    private String lastEmailAddress;

    @Override
    public void mail(String emailAddress, String message) {
        this.lastEmailAddress = emailAddress;
    }

    public void assertLastEmailAddress(String emailAddress) {
        assertThat(lastEmailAddress).isEqualTo(emailAddress);
    }
}
