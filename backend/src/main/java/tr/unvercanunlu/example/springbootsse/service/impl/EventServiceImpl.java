package tr.unvercanunlu.example.springbootsse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.mapper.EventMapper;
import tr.unvercanunlu.example.springbootsse.model.Event;
import tr.unvercanunlu.example.springbootsse.service.EventService;

import java.util.Hashtable;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    public static final Hashtable<SseEmitter, List<Event>> EMITTERS_WITH_EVENTS = new Hashtable<>();

    @Value("${sse-reconnect}")
    private Long reconnectInMilliseconds;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public void send(SseEmitter emitter, Event event) {
        try {
            SseEmitter.SseEventBuilder builder = eventMapper.map(event, reconnectInMilliseconds);
            LOGGER.debug("Event is created.");

            emitter.send(builder);
            LOGGER.debug("Event is sent to Emitter.");
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.debug("Emitter is dead.");

            EMITTERS_WITH_EVENTS.remove(emitter);
            LOGGER.debug("Emitter is removed from Emitter .");
        }
    }
}
