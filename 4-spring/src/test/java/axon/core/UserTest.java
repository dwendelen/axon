package axon.core;

import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailAddressCommand;
import axon.core.event.EmailAddressUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
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

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    //1 USER AGGREGATE
    @Test
    public void whenIRegisterAUser_thenAUserIsRegistered() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, OLD_EMAIL);

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(registerUserCommand.getUuid(), NAME, OLD_EMAIL);

        fixture.given()
                .when(registerUserCommand)
                .expectEvents(userRegisteredEvent);
    }

    @Test
    public void whenIRegisterAUserWithoutName_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand("", OLD_EMAIL);

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
}
