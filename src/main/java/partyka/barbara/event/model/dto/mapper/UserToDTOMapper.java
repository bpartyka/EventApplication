package partyka.barbara.event.model.dto.mapper;

import partyka.barbara.event.model.User;
import partyka.barbara.event.model.dto.UserDTO;

import java.util.stream.Collectors;

public class UserToDTOMapper {
    private UserToDTOMapper() {
    }

    public static UserDTO map(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getEvents().stream()
                .map(EventToDTOMapper::map).collect(Collectors.toSet()));
    }
}
