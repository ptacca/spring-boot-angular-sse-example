package tr.unvercanunlu.example.springbootsse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.model.Event;

import java.util.ArrayList;

import static tr.unvercanunlu.example.springbootsse.service.impl.EventServiceImpl.EMITTERS_WITH_EVENTS;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Value("${sse-timeout}")
    private Long timeoutInMilliseconds;

    @GetMapping(value = "/subscribe")
    public ResponseEntity<SseEmitter> subscribe() {
        SseEmitter emitter = new SseEmitter(timeoutInMilliseconds);
        LOGGER.debug("Emitter is initialized.");

        emitter.onError(throwable -> {
            LOGGER.error("Emitter has error.");
            throwable.printStackTrace();
            LOGGER.debug("Emitter is removed from Emitter Table on error.");
        });

        emitter.onCompletion(() -> {
            LOGGER.error("Emitter is completed.");
            EMITTERS_WITH_EVENTS.remove(emitter);
            LOGGER.debug("Emitter is removed from Emitter Table on completion.");
        });

        emitter.onTimeout(() -> {
            LOGGER.error("Emitter has timeout.");
            EMITTERS_WITH_EVENTS.remove(emitter);
            LOGGER.debug("Emitter is removed from Emitter Table on timeout.");
        });

        EMITTERS_WITH_EVENTS.put(emitter, new ArrayList<>());
        LOGGER.debug("Emitter is added to Emitter Table.");

        return ResponseEntity.ok(emitter);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> getEvent(@RequestBody Event event) {
        LOGGER.debug("New event is got.");

        return ResponseEntity.ok(event);
    }
}
