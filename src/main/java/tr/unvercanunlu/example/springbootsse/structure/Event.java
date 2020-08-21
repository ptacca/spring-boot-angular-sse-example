package tr.unvercanunlu.example.springbootsse.structure;

import java.time.LocalDateTime;

public abstract class Event {

    private LocalDateTime timestamp;

    private EventType type;

    private EventStatus status;

    private EventPriority priority;

    private String name;

    private Object metadata;

    private String description;

    public Event() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                ", status=" + status +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", metadata=" + metadata +
                ", description='" + description + '\'' +
                '}';
    }
}


