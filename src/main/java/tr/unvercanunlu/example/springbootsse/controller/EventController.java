package tr.unvercanunlu.example.springbootsse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.service.impl.EventServiceImpl;
import tr.unvercanunlu.example.springbootsse.structure.Event;
import tr.unvercanunlu.example.springbootsse.structure.EventPriority;
import tr.unvercanunlu.example.springbootsse.structure.EventStatus;
import tr.unvercanunlu.example.springbootsse.structure.EventType;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Value("${sse-timeout}")
    private Long timeoutInMilliseconds;

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping(value = "/subscribe")
    public ResponseEntity<SseEmitter> subscribe() {
        SseEmitter emitter = new SseEmitter(timeoutInMilliseconds);
        LOGGER.info("Emitter is initialized.");

        emitter.onError(throwable -> {
            throwable.printStackTrace();
            LOGGER.error("Emitter has error: " + throwable.getMessage());
        });

        emitter.onCompletion(() -> {
            eventService.getEmitters().remove(emitter);
            LOGGER.info("Emitter is removed from Emitter List on completion.");
        });

        emitter.onTimeout(() -> {
            eventService.getEmitters().remove(emitter);
            LOGGER.info("Emitter is removed from Emitter List on timeout.");
        });

        eventService.getEmitters().add(emitter);
        LOGGER.info("Emitter is added to Emitter list.");

        return ResponseEntity.ok(emitter);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> getEvents(@RequestBody Event event) {
        LOGGER.info("New event has been got.");
        this.eventService.get(event);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/test")
    public ResponseEntity<?> test() {
        class CustomEvent extends Event {
        }
        LOGGER.info("Custom event class is created.");

        CustomEvent customEvent = new CustomEvent();
        customEvent.setName("custom");
        customEvent.setDescription("description");
        customEvent.setPriority(EventPriority.values()[new Random().nextInt(EventPriority.values().length)]);
        customEvent.setStatus(EventStatus.values()[new Random().nextInt(EventStatus.values().length)]);
        customEvent.setType(EventType.values()[new Random().nextInt(EventType.values().length)]);
        customEvent.setTimestamp(LocalDateTime.now());
        customEvent.setMetadata("metadata");
        LOGGER.info("Custom event is created.");

        this.eventService.send(customEvent);
        LOGGER.info("Custom event is sent.");

        return ResponseEntity.ok(customEvent);
    }
}
