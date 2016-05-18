package axon.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

import static org.assertj.core.api.Assertions.assertThat;

public class UITest {
    private JLineShellComponent shell;

    @Before
    public void startUp() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        shell = bootstrap.getJLineShellComponent();
    }

    @After
    public void shutdown() {
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
    @Test
    public void register_login_linkSteamAccount_buyGame() {
        register();
        registerGame();
        login();
        linkSteamAccount();
        buyGame();
        validateSteam();
    }

    @Test
    public void register_login_buyGame_linkSteamAccount() {
        register();
        registerGame();
        login();
        buyGame();
        validateNoSteam();
        linkSteamAccount();
        validateSteam();
    }

    @Test
    public void register_login_buyGame_buyGameAgain() {
        register();
        registerGame();
        login();
        buyGame();
        buyAlreadyBoughtGame();
    }

    private void registerGame() {
        CommandResult commandResult = shell.executeCommand("register-game --name myGame --steamId myGameSteam");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Registered myGame with streamId myGameSteam");
    }

    private void linkSteamAccount() {
        CommandResult commandResult = shell.executeCommand("link-steam steamUserId");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Steam id steamUserId linked");
    }

    private void buyGame() {
        CommandResult commandResult = shell.executeCommand("buy-game myGame");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("You bought myGame");
    }

    private void buyAlreadyBoughtGame() {
        CommandResult commandResult = shell.executeCommand("buy-game myGame");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("Could not buy myGame because you already bought it");
    }

    private void validateSteam() {
        CommandResult commandResult = shell.executeCommand("steam list");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("steamUserId     myGameSteam\n");
    }

    private void validateNoSteam() {
        CommandResult commandResult = shell.executeCommand("steam list");
        assertThat(commandResult.isSuccess()).isTrue();
        assertThat(commandResult.getResult()).isEqualTo("");
    }

    //6 TODO
}
