package axon.core;

import axon.core.infrastructure.steam.SteamGateway;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class SteamRegistrationSaga extends AbstractAnnotatedSaga {
    @Autowired
    private transient SteamGateway steamGateway;
    @Autowired
    private transient SteamIdLookup steamIdLookup;

    private UUID userId;
    private String gameSteamId;

    @StartSaga
    @SagaEventHandler(associationProperty = "purchaseId")
    public void handle(GameBoughtEvent gameBoughtEvent) {
        this.gameSteamId = steamIdLookup.getSteamIdForGame(gameBoughtEvent.getGameId());
        this.userId = gameBoughtEvent.getUserId();

        associateWith("userId", this.userId.toString());

        Optional<String> steamIdForUser = steamIdLookup.getSteamIdForUser(gameBoughtEvent.getUserId());
        if(!steamIdForUser.isPresent()) {
            return;
        }

        notifySteam(steamIdForUser.get());
    }

    @SagaEventHandler(associationProperty = "userId")
    public void handle(SteamAccountLinkedEvent steamAccountLinkedEvent) {
        notifySteam(steamAccountLinkedEvent.getSteamId());
    }

    private void notifySteam(String userSteamId) {
        steamGateway.registerGame(userSteamId, gameSteamId);
        end();
    }
}
