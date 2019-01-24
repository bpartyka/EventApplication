package partyka.barbara.event.model.dto;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Set<EventDTO> events;

    public UserDTO(Long id, String name, String surname, String email, Set<EventDTO> events) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDTO> events) {
        this.events = events;
    }
}
