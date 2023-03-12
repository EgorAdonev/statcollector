/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.adonev.statcollector.exception.StatCollectorError;
import ru.adonev.statcollector.model.EventType;
import ru.adonev.statcollector.model.Event;
import ru.adonev.statcollector.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/api")
public class EventController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestBody @Valid Event newEvent) {
        boolean isDateNull = newEvent.getEventDate() == null;
        boolean isEventNull = newEvent.getEvent() == null;
        if (isDateNull) {
            return new ResponseEntity<>(new StatCollectorError(HttpStatus.BAD_REQUEST.value(), "Date value is null(check JSON attribute name)"),HttpStatus.BAD_REQUEST);
        } else if(isEventNull) {
            return new ResponseEntity<>(new StatCollectorError(HttpStatus.BAD_REQUEST.value(), "Event value is null(check JSON attribute name)"),HttpStatus.BAD_REQUEST);
        } else {
            Event event = eventService.save(newEvent);
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity<?> getEvents(@RequestParam("filter") String filter, @RequestParam("page") @Min(0) int page, @RequestParam("size") @Min(1) int size) {
        Pageable sortedByDays = null;
        try {
            sortedByDays = PageRequest.of(page, size);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new StatCollectorError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        Page<Event> results = eventService.findByEvent(EventType.valueOf(filter), sortedByDays);
        List<Event> eventsList = results.getContent().stream()
                .collect(groupingBy(Event::getEventDate,
                        Collectors.toList()))
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(toList());
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }

}
