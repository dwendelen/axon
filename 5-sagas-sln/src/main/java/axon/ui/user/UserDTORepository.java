package axon.ui.user;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDTORepository {
    private Map<String, UserDTO> users = new HashMap<>();

    public UserDTO getUser(String name) throws IllegalArgumentException {
        if(!users.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return users.get(name);
    }

    public Collection<UserDTO> getAllUsers() {
        return users.values();
    }

    public void add(UserDTO userDTO) {
        users.put(userDTO.getName(), userDTO);
    }
}