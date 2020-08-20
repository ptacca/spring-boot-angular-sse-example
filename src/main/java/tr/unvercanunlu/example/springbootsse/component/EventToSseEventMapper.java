package tr.unvercanunlu.example.springbootsse.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import tr.unvercanunlu.example.springbootsse.constant.DateTimeFormat;
import tr.unvercanunlu.example.springbootsse.model.Event;

import java.time.format.DateTimeFormatter;

@Component
public class EventToSseEventMapper {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventToSseEventMapper.class);

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(DateTimeFormat.TIMESTAMP_FORMAT);

    public SseEventBuilder map(Event event, Long reconnectInMilliseconds) {
        SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name(event.getName().toLowerCase())
                .data(event)
                .id(event.getTimestamp().format(TIMESTAMP_FORMATTER))
                .comment(event.getDescription())
                .reconnectTime(reconnectInMilliseconds);

        LOGGER.info("Event is mapped to SseEvent: " + event.getName().toLowerCase());

        return sseEventBuilder;
    }
}
