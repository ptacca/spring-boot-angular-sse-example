package tr.unvercanunlu.example.springbootsse.service;

import tr.unvercanunlu.example.springbootsse.structure.Event;

public interface EventService {

    Event get();

    void send(Event event);
}
