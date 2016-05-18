package axon.core.steam;

import axon.core.infrastructure.steam.SteamGateway;
import axon.core.user.event.GameBoughtEvent;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.springframework.beans.factory.annotation.Autowired;

public class SteamRegistrationSaga extends AbstractAnnotatedSaga {
    @Autowired
    private transient SteamGateway steamGateway;

    public void handle(GameBoughtEvent gameBoughtEvent) {
        throw new UnsupportedOperationException();
    }

    //TODO Implement and Complete
}
