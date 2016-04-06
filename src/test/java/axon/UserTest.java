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
    public void testCreate() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.name = NAME;

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        userCreatedEvent.uuid = createUserCommand.uuid;
        userCreatedEvent.name = NAME;

        fixture.given()
                .when(createUserCommand)
                .expectEvents(userCreatedEvent);
    }
}
