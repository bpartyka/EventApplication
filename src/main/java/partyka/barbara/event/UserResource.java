package partyka.barbara.event;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserResource {

    private UserRepository userRepository;
    private EventRepository eventRepository;

    public UserResource(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    @GetMapping("users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("users/byName/{name}")
    public List<User> getAllUsersByName(@PathVariable(value = "name") String name) {

        return userRepository.findUsersByName(name);
    }

    @PostMapping("users/ev/{eventId}")
    public User addUser(@RequestBody User user, @PathVariable(value = "eventId") Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        HashSet<Event> events = new HashSet<>(Arrays.asList(event));

        user.setEvents(events);
//        user.setEvents(new HashSet<>(eventRepository.findAll()));
//        event.setUsers(new HashSet<>(Arrays.asList(user)));
//        eventRepository.save(event);

        return userRepository.save(user);
    }

    @PostMapping("users")
    public User addUser2(@RequestBody User user) {

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

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            userRepository.delete(optionalUser.get());
            return new ResponseEntity<>(HttpStatus.OK); //200
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
    }

}
