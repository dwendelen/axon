package axon.core.infrastructure.steam;

public interface SteamGateway {
    void registerGame(String steamUserId, String steamGameId);
}
