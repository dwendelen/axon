package axon.core.user;

import axon.core.user.command.BuyGameCommand;
import axon.core.user.command.LinkSteamAccountCommand;
import axon.core.user.command.RegisterUserCommand;
import axon.core.user.command.UpdateEmailAddressCommand;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import axon.core.user.event.UserRegisteredEvent;
import axon.core.user.exception.GameAlreadyBoughtException;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserTest {
    public static final String NAME = "name";
    public static final UUID USER_UUID = UUID.randomUUID();
    public static final String OLD_EMAIL = "old email";
    public static final String NEW_EMAIL = "email";
    public static final String STEAM_ID = "SteamId";
    public static final UUID GAME_ID = UUID.randomUUID();

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    //1 USER AGGREGATE
    @Test
    public void whenIRegisterAUser_thenAUserIsRegistered() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, NEW_EMAIL);

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(registerUserCommand.getUserId(), NAME, NEW_EMAIL);

        fixture.given()
                .when(registerUserCommand)
                .expectEvents(userRegisteredEvent);
    }

    @Test
    public void whenIRegisterAUserWithoutName_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand("", NEW_EMAIL);

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenIRegisterAUserWithoutAnEmailAddress_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, "");

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddress_thenTheEmailAddressIsUpdated() throws Exception {
        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL))
                .when(new UpdateEmailAddressCommand(USER_UUID, NEW_EMAIL))
                .expectEvents(new EmailAddressUpdatedEvent(USER_UUID, NEW_EMAIL));
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddressToAnEmptyOne_thenAnExceptionIsThrown() throws Exception {
        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL))
                .when(new UpdateEmailAddressCommand(USER_UUID, ""))
                .expectException(IllegalArgumentException.class);
    }

    //2 EVENT LISTENERS
    //3 READ MODELS
    //4 SPRING
    //5 SAGAS
    //6 TODO

    @Test
    public void givenAUser_whenILinkASteamAccount_thenASteamAccountIsLinked() throws Exception {
        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL))
                .when(new LinkSteamAccountCommand(USER_UUID, STEAM_ID))
                .expectEvents(new SteamAccountLinkedEvent(USER_UUID, STEAM_ID));
    }

    @Test
    public void givenAUser_whenIBuyAGame_thenTheGameIsBought() throws Exception {
        BuyGameCommand buyGameCommand = new BuyGameCommand(USER_UUID, GAME_ID);
        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL))
                .when(buyGameCommand)
                .expectEvents(new GameBoughtEvent(USER_UUID, GAME_ID, buyGameCommand.getPurchaseId()));
    }

    @Test
    public void givenAUserThatBoughtAGame_whenIBuyThatGameAgain_thenAnExceptionIsThrown() throws Exception {
        fixture.given(
                    new UserRegisteredEvent(USER_UUID, NAME, OLD_EMAIL),
                    new GameBoughtEvent(USER_UUID, GAME_ID, UUID.randomUUID())
                )
                .when(new BuyGameCommand(USER_UUID, GAME_ID))
                .expectException(GameAlreadyBoughtException.class);
    }
}
