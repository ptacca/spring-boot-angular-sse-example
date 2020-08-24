package tr.unvercanunlu.example.springbootsse.service;

import tr.unvercanunlu.example.springbootsse.structure.Event;

public interface EventService {

    void get(Event event);

    void send(Event event);
}
