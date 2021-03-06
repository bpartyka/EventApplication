package partyka.barbara.event.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import partyka.barbara.event.model.Event;
import partyka.barbara.event.model.User;
import partyka.barbara.event.model.dto.UserDTO;
import partyka.barbara.event.repository.EventRepository;
import partyka.barbara.event.repository.UserRepository;
import partyka.barbara.event.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserResource {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private UserService userService;

    public UserResource(UserRepository userRepository, EventRepository eventRepository, UserService userService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.userService = userService;
    }


    @GetMapping("users")
    public List<UserDTO> getUser() {
        return userService.findAll();
    }

    @GetMapping("users/{userName}")
    public List<UserDTO> getUsersByName(@PathVariable String userName) {
        return userService.findAllByName(userName);
    }

    @PostMapping("users/ev/{eventId}")
    public ResponseEntity<User> addUser(@RequestBody User user, @PathVariable(value = "eventId") Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            HashSet<Event> events = new HashSet<>(Arrays.asList(optionalEvent.get()));
            user.setEvents(events);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("users")
    public User addUser(@RequestBody User user) {

        return userRepository.save(user);
    }


    @PutMapping("users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());

                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }


    @PutMapping("users/opt/{id}")
    public ResponseEntity<User> updateEvent(@PathVariable Long id, @RequestBody User user) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            optionalUser.get().setName(user.getName());
            return new ResponseEntity<>(userRepository.save(optionalUser.get()), HttpStatus.OK); // 200
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

    @DeleteMapping("users/opt/{id}")
    public ResponseEntity<User> deleteUserOpt(@PathVariable Long id) {
        return userService.deleteById(id) ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
