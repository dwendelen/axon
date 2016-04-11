package axon.ui.user;

import org.junit.Test;

public class UserDTORepositoryTest {
    //3 READ MODELS
    @Test(expected = IllegalArgumentException.class)
    public void whenAnUnknownUserIsFetched_thenAnExceptionIsThrown() {
        new UserDTORepository().getUser("Unknown");
    }

    //4 SPRING
}
