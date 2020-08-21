package tr.unvercanunlu.example.springbootsse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.model.ProductEvent;
import tr.unvercanunlu.example.springbootsse.service.impl.EventServiceImpl;
import tr.unvercanunlu.example.springbootsse.structure.Event;

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

    @GetMapping(value = "/test")
    public ResponseEntity<?> generateSimpleEventAndSend() {
        Event productEvent = new ProductEvent();
        LOGGER.info("Product Event is created: " + productEvent.toString());

        this.eventService.send(productEvent);
        LOGGER.info("Product Event is sent to Emitter List: " + productEvent.toString());

        return ResponseEntity.noContent().build();
    }
}
