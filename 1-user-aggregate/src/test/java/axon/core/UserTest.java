package axon.core;

import axon.core.User;
import axon.core.command.RegisterUserCommand;
import axon.core.event.UserRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserTest {
    public static final String NAME = "name";
    public static final UUID USER_UUID = UUID.randomUUID();

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    //1 USER AGGREGATE
    @Test
    public void whenIRegisterAUser_thenAUserIsRegistered() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME); //TODO

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(registerUserCommand.getUuid()); //TODO

        fixture.given()
                .when(registerUserCommand)
                .expectEvents(userRegisteredEvent);
    }

    @Test
    public void whenIRegisterAUserWithoutName_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(""); //TODO

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenIRegisterAUserWithoutAnEmailAddress_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME); //TODO

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddress_thenTheEmailAddressIsUpdated() throws Exception {
        fixture.given(new UserRegisteredEvent(USER_UUID)) //TODO
                .when(null) //TODO
                .expectEvents(); //TODO
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddressToAnEmptyOne_thenAnExceptionIsThrown() throws Exception {
        throw new UnsupportedOperationException(); //TODO
    }
}
