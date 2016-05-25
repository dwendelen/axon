package axon.core.user;

import axon.core.user.command.BuyGameCommand;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.UserRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserTest {
    public static final String NAME = "name";
    public static final UUID USER_UUID = UUID.randomUUID();
    public static final String OLD_EMAIL = "old email";
    public static final UUID GAME_ID = UUID.randomUUID();

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    @Test
    public void givenAUser_whenIBuyAGame_thenTheGameIsBought() throws Exception {
        BuyGameCommand buyGameCommand = new BuyGameCommand(USER_UUID, GAME_ID);
        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL))
                .when(buyGameCommand)
                .expectEvents(new GameBoughtEvent(USER_UUID, GAME_ID, buyGameCommand.getPurchaseId()));
    }
}
