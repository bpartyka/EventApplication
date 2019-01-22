package partyka.barbara.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class EventResource {

    private EventRepository eventRepository;

    public EventResource(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("events")
    public List<Event> getEvent() {
        return eventRepository.findAll();
    }

    @PostMapping("events")
    public Event addEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    //     @RequestMapping(value = "events/{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, headers = "Accept=*/*")
    @PutMapping("events/{id}")
    public Event replaceEvent(@RequestBody Event newEvent, @PathVariable Long id) {

        return eventRepository.findById(id)
                .map(event -> {
                    event.setName(newEvent.getName());

                    return eventRepository.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return eventRepository.save(newEvent);
                });
    }

    @DeleteMapping("events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }


//    @PutMapping("events/{isbn}")
//    public ResponseEntity<Event> updateEvent(@PathVariable String isbn, @RequestBody Event event) {
//
//        Optional<Event> optionalEvent = eventRepository.findByIsbn(isbn);
//
//        if (optionalEvent.isPresent()) {
//            optionalEvent.get().setName(event.getName());
//            optionalEvent.get().setIsbn(event.getIsbn());
//            return new ResponseEntity<>(eventRepository.save(optionalEvent.get()), HttpStatus.OK); // 200
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
//    }
//
//    @DeleteMapping("events/{isbn}")
//    public ResponseEntity<Event> deleteBook(@PathVariable String isbn) {
//
//        Optional<Event> optionalEvent = eventRepository.findByIsbn(isbn);
//
//        if (optionalEvent.isPresent()) {
//
//            optionalEvent.delete(optionalEvent.get());
//            return new ResponseEntity<>(HttpStatus.OK); //200
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
//    }

}

