package tr.unvercanunlu.example.springbootsse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.event.SimpleEvent;
import tr.unvercanunlu.example.springbootsse.model.Event;
import tr.unvercanunlu.example.springbootsse.service.impl.EventServiceImpl;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Value("${sse-timeout}")
    private Long timeoutInMilliseconds;

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping(value = "/subscribe")
    public SseEmitter subscribe() {
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

        return emitter;
    }

    // Test
    @GetMapping(value = "/testSimpleEvent")
    public void generateSimpleEventAndSend() {
        Event simpleEvent = new SimpleEvent();
        LOGGER.info("Simple Event is created: " + simpleEvent.toString());

        this.eventService.send(simpleEvent);
        LOGGER.info("Simple Event is sent to Emiter List: " + simpleEvent.toString());
    }
}
