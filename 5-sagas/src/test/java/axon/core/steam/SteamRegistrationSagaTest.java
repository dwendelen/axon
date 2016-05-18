package axon.core.steam;

import axon.core.infrastructure.steam.SteamGatewayMock;
import axon.core.user.event.GameBoughtEvent;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

public class SteamRegistrationSagaTest {
    public static final UUID USER_ID = UUID.randomUUID();
    public static final UUID PURCHASE_ID = UUID.randomUUID();
    public static final UUID GAME_ID = UUID.randomUUID();
    public static final String GAME_NAME = "gameName";
    public static final String GAME_STEAM_ID = "gameSteamId";
    public static final String USER_STEAM_ID = "userSteamId";

    private AnnotatedSagaTestFixture fixture;

    private SteamGatewayMock steamGatewayMock = new SteamGatewayMock();
    private SteamIdLookupStub steamIdLookupStub = new SteamIdLookupStub();

    @Before
    public void setUp() {
        fixture = new AnnotatedSagaTestFixture(SteamRegistrationSaga.class);
        fixture.registerResource(steamGatewayMock);
        fixture.registerResource(steamIdLookupStub);
    }

    //1 USER AGGREGATE
    //2 EVENT LISTENERS
    //3 READ MODELS
    //4 SPRING
    //5 SAGAS

    @Test
    public void givenAUserWithoutSteamId_whenTheUsersBuysAGame_thenSteamIsNotNotified() throws Exception {
        fixture.whenPublishingA(new GameBoughtEvent(/* TODO */))
                .expectActiveSagas(1);

        steamGatewayMock.assertNoGameRegistered();
    }

    @Test
    public void givenAUserWithoutSteamId_thatBoughtAGame_whenTheUserLinksHisSteamAccount_thenSteamIsNotified() throws Exception {
        fixture.givenAPublished(new GameBoughtEvent(/* TODO */))
                .whenPublishingA(null) //TODO Link steam account
                .expectActiveSagas(0);

        steamGatewayMock.assertRegistered(USER_STEAM_ID, GAME_STEAM_ID);
    }

    @Test
    public void givenAUserWithSteamId_whenTheUserBuysAGame_thenSteamIsNotified() throws Exception {
        steamIdLookupStub.registerUser();
        fixture.whenPublishingA(new GameBoughtEvent(/* TODO */))
                .expectActiveSagas(0);

        steamGatewayMock.assertRegistered(USER_STEAM_ID, GAME_STEAM_ID);
    }

    //6 TODO

    private static class SteamIdLookupStub extends SteamIdLookup {
        private boolean userRegistered = false;

        public void registerUser() {
            userRegistered = true;
        }

        public Optional<String> getSteamIdForUser(UUID userId) {
            if(userRegistered) {
                return Optional.of(USER_STEAM_ID);
            } else {
                return Optional.empty();
            }
        }

        public String getSteamIdForGame(UUID gameId) {
            return GAME_STEAM_ID;
        }
    }
}