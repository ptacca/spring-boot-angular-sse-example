package tr.unvercanunlu.example.springbootsse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import tr.unvercanunlu.example.springbootsse.config.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Event {

    private String name;

    private String description;

    @JsonFormat(pattern = DateTimeFormat.UTC_DATETIME_FORMAT)
    private LocalDateTime timestamp;

    private EventType type;

    private EventStatus status;

    private EventPriority priority;

    private Object data;

    public Event() {
        this.setTimestamp(ZonedDateTime.now().withZoneSameLocal(ZoneId.of("UTC")).toLocalDateTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + "'" +
                ", description='" + description + "'" +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", status=" + status +
                ", priority=" + priority +
                ", data=" + data +
                '}';
    }
}
