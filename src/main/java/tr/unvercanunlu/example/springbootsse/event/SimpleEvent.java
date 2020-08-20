package tr.unvercanunlu.example.springbootsse.event;

import tr.unvercanunlu.example.springbootsse.model.Event;
import tr.unvercanunlu.example.springbootsse.model.EventPriority;
import tr.unvercanunlu.example.springbootsse.model.EventStatus;
import tr.unvercanunlu.example.springbootsse.model.EventType;

import java.time.LocalDateTime;

public class SimpleEvent extends Event {

    public SimpleEvent() {
        this.setName("simple");
        this.setPriority(EventPriority.LOW);
        this.setType(EventType.NOTIFICATION);
        this.setStatus(EventStatus.SUCCESSFUL);
        this.setComment("comment");
        this.setTimestamp(LocalDateTime.now());
        this.setData("data");
    }
}
