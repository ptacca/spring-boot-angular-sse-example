package tr.unvercanunlu.example.springbootsse.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import tr.unvercanunlu.example.springbootsse.config.DateTimeFormat;
import tr.unvercanunlu.example.springbootsse.model.Event;

import java.time.format.DateTimeFormatter;

@Component
public class EventMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMapper.class);

    public SseEventBuilder map(Event event, Long reconnectInMilliseconds) {
        SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name(event.getName().toLowerCase())
                .data(event)
                .id(event.getTimestamp().format(DateTimeFormatter.ofPattern(DateTimeFormat.UTC_DATETIME_FORMAT)))
                .comment(event.getDescription())
                .reconnectTime(reconnectInMilliseconds);

        LOGGER.debug("Event is mapped to SseEvent: " + event.getName().toLowerCase());

        return sseEventBuilder;
    }
}
