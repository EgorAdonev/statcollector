/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.adonev.statcollector.model.Event;
import ru.adonev.statcollector.model.EventType;
import ru.adonev.statcollector.repository.EventRepository;
@Service
public class EventService {
    private final EventRepository repository;
    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }
    public Page<Event> findByEvent(EventType event, Pageable pageable){
        return repository.findByEvent(event,pageable);

    }
    public Event save(Event event){
        return repository.save(event);
    }
}
