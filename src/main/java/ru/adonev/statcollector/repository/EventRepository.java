/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.adonev.statcollector.model.Event;
import ru.adonev.statcollector.model.EventType;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Page<Event> findByEvent(@Param("event") EventType event, Pageable pageable);
    Event save(Event persisted);


}