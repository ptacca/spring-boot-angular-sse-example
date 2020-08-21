package tr.unvercanunlu.example.springbootsse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.component.EventToSseEventMapper;
import tr.unvercanunlu.example.springbootsse.service.EventService;
import tr.unvercanunlu.example.springbootsse.structure.Event;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    private List<SseEmitter> emitters;

    @Value("${sse-reconnect}")
    private Long reconnectInMilliseconds;

    @Autowired
    private EventToSseEventMapper eventToSseEventMapper;

    public EventServiceImpl() {
        this.emitters = new ArrayList<>();
    }

    public List<SseEmitter> getEmitters() {
        return emitters;
    }

    public void setEmitters(List<SseEmitter> emitters) {
        this.emitters = emitters;
    }

    @Override
    public Event get() {
        LOGGER.info("Under construction!");
        return null;
    }

    @Override
    public void send(Event event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        LOGGER.info("Dead Emitter List is created.");

        this.emitters.forEach(emitter -> {
            try {
                SseEmitter.SseEventBuilder builder = eventToSseEventMapper.map(event, reconnectInMilliseconds);
                LOGGER.info("Event is created.");

                emitter.send(builder);
                LOGGER.info("Event is sent to Emitter.");
            } catch (Exception exception) {
                deadEmitters.add(emitter);
                exception.printStackTrace();
                LOGGER.error("Emitter is added to Dead Emitter List: " + exception.getMessage());
            }
        });

        deadEmitters.forEach(emitter -> {
            this.emitters.remove(emitter);
            LOGGER.info("Dead Emitter is removed from Emitter List.");
        });
    }
}
