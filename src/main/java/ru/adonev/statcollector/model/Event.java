/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Event {
    public Event() {
    }

    public Event(int id, EventType event, LocalDate eventDate) {
        this.id = id;
        this.event = event;
        this.eventDate = eventDate;
    }

    public Event(EventType event, LocalDate eventDate) {
        this.event = event;
        this.eventDate = eventDate;
    }
    @Id
    @SequenceGenerator(
            name="event_seq",
            sequenceName = "event_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private int id;
    private LocalDate eventDate;
    @Enumerated(EnumType.STRING)
    //для меньшего использования данных нужно использовать EnumType.ORDINAL
    private EventType event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
