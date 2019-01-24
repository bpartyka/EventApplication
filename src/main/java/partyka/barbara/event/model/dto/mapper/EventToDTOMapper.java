package partyka.barbara.event.model.dto.mapper;

import partyka.barbara.event.model.Event;
import partyka.barbara.event.model.dto.EventDTO;

public class EventToDTOMapper {
    private EventToDTOMapper() {
    }

    public static EventDTO map(Event event) {
        return new EventDTO(event.getId(), event.getName());
    }
}
