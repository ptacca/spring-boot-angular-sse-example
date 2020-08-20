package tr.unvercanunlu.example.springbootsse.model;

import java.time.LocalDateTime;

public abstract class Event {

    private LocalDateTime timestamp;

    private EventType type;

    private EventStatus status;

    private EventPriority priority;

    private String name;

    private Object data;

    private String comment;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
                ", data=" + data +
                ", comment='" + comment + '\'' +
                '}';
    }
}


