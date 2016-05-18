package axon.core.infrastructure.steam;

import static org.assertj.core.api.Assertions.assertThat;

public class SteamGatewayMock implements SteamGateway {
    private String steamUserId;
    private String steamGameId;

    @Override
    public void registerGame(String steamUserId, String steamGameId) {
        this.steamGameId = steamGameId;
        this.steamUserId = steamUserId;
    }

    public void assertNoGameRegistered() {
        assertThat(steamUserId).isNull();
    }

    public void assertRegistered(String steamUserId, String steamGameId) {
        assertThat(this.steamUserId).isEqualTo(steamUserId);
        assertThat(this.steamGameId).isEqualTo(steamGameId);
    }
}