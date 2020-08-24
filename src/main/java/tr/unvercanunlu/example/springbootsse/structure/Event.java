package tr.unvercanunlu.example.springbootsse.structure;

import com.fasterxml.jackson.annotation.JsonFormat;
import tr.unvercanunlu.example.springbootsse.constant.DateTimeFormat;

import java.time.LocalDateTime;

public class Event {

    private String name;

    private EventType type;

    private EventStatus status;

    private EventPriority priority;

    private String metadata;

    @JsonFormat(pattern = DateTimeFormat.TIMESTAMP_FORMAT)
    private LocalDateTime timestamp;

    private String description;

    public Event() {
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

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", priority=" + priority +
                ", metadata='" + metadata + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}
