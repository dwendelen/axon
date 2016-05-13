package axon.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

import static org.assertj.core.api.Assertions.assertThat;

public class UITest {
    private static JLineShellComponent shell;

    @BeforeClass
    public static void startUp() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        shell = bootstrap.getJLineShellComponent();
    }

    @AfterClass
    public static void shutdown() {
        shell.stop();
    }

    //3 READ MODELS
    @Test
    public void register_logIn_updateEmailAddress_logout() throws Exception {
        register();
        validateRegisterationMail();
        login();
        updateEmailAddress();
        validateUpdateEmailAddressMail();
        logout();
    }

    private void register() {
        CommandResult commandResult = shell.executeCommand("register --name Test --email test@junit.org");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Registered Test with email address test@junit.org");
    }

    private void validateRegisterationMail() {
        CommandResult commandResult = shell.executeCommand("mail list");
        assertThat(commandResult.isSuccess());
        assertThat(commandResult.getResult()).isEqualTo(
                "  0:   test@junit.org\n"
        );

        commandResult = shell.executeCommand("mail show 0");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo(
                "To: test@junit.org\n" +
                "\n" +
                "Welcome!");
    }

    private void login() {
        CommandResult commandResult = shell.executeCommand("login Test");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Welcome Test");
    }

    private void updateEmailAddress() {
        CommandResult commandResult = shell.executeCommand("update-email new@junit.org");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Updated email address to new@junit.org");
    }

    private void validateUpdateEmailAddressMail() {
        CommandResult commandResult = shell.executeCommand("mail list");
        assertThat(commandResult.isSuccess());
        assertThat(commandResult.getResult()).isEqualTo(
                "  0:   test@junit.org\n" +
                "  1:   new@junit.org\n"
        );

        commandResult = shell.executeCommand("mail show 1");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo(
                "To: new@junit.org\n" +
                "\n" +
                "Welcome!");
    }

    private void logout() {
        CommandResult commandResult = shell.executeCommand("logout");
        assertThat(commandResult.isSuccess());
        assertThat(commandResult.getResult()).isEqualTo("Logged out");
    }

    //4 SPRING
    //5 SAGAS
}
