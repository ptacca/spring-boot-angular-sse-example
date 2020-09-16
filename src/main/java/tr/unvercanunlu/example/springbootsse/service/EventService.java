package tr.unvercanunlu.example.springbootsse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tr.unvercanunlu.example.springbootsse.model.Event;

public interface EventService {

    void send(SseEmitter emitter, Event event);
}
