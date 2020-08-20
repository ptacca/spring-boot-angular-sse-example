package tr.unvercanunlu.example.springbootsse.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${sse-reconnect}")
    private Long reconnectInMilliseconds;

    public SseEventBuilder map(Event event) {
        String eventNameAsKebabCase = String.join("-",
                event.getName().toLowerCase(),
                event.getType().name().toLowerCase(),
                event.getPriority().name().toLowerCase(),
                event.getStatus().name().toLowerCase()
        );

        SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name(eventNameAsKebabCase)
                .data(event.getData())
                .id(event.getTimestamp().format(TIMESTAMP_FORMATTER))
                .comment(event.getComment())
                .reconnectTime(reconnectInMilliseconds);

        LOGGER.info("Event is mapped to SseEvent: " + eventNameAsKebabCase);

        return sseEventBuilder;
    }
}
