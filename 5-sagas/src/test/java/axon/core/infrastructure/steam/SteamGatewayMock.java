package axon.core.infrastructure.steam;

public class SteamGatewayMock implements SteamGateway {
    @Override
    public void registerGame(String steamUserId, String steamGameId) {
        throw new UnsupportedOperationException();
    }
}
