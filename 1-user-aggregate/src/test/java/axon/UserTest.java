package axon;

import axon.command.CreateUserCommand;
import axon.event.UserCreatedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    public static final String NAME = "name";

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    @Test
    public void whenICreateAUser_thenAUserIsCreated() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand(NAME);

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(createUserCommand.getUuid(), NAME);

        fixture.given()
                .when(createUserCommand)
                .expectEvents(userCreatedEvent);
    }

    @Test
    public void whenICreateAUserWithoutName_thenAnExceptionIsThrown() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("");

        fixture.given()
                .when(createUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenICreateAUserWithoutAnEmailAddress_thenAnExceptionIsThrown() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand(NAME);

        fixture.given()
                .when(createUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddress_thenTheEmailAddressIsUpdated() throws Exception {
        fixture.given(new CreateUserCommand(NAME))
                .when(null) //TODO
                .expectEvents(); //TODO
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddressToAnEmptyOne_thenAnExceptionIsThrown() throws Exception {
        throw new UnsupportedOperationException(); //TODO
    }
}
