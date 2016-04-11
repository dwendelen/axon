package axon.ui;

import axon.ui.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Context {
    private UserDTO currentUser;

    public UUID getCurrentUUID() {
        return currentUser.getUUID();
    }

    public void loggedIn(UserDTO user) {
        currentUser = user;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
    }
}
