package axon.core.game;

import axon.core.game.command.RegisterGameCommand;
import axon.core.game.event.GameRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    public static final String STEAM_ID = "steam";
    public static final String NAME = "game";
    private FixtureConfiguration<Game> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(Game.class);
    }

    //1 USER AGGREGATE
    //2 EVENT LISTENERS
    //3 READ MODELS
    //4 SPRING
    //5 SAGAS
    //6 TODO

    @Test
    public void name() throws Exception {
        RegisterGameCommand registerGameCommand = new RegisterGameCommand(NAME, STEAM_ID);

        fixture.given()
                .when(registerGameCommand)
                .expectEvents(new GameRegisteredEvent(registerGameCommand.getGameId(), NAME, STEAM_ID));
    }
}
