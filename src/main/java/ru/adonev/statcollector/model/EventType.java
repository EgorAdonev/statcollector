/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.model;

public enum EventType {
    EVENT_1("event1"),EVENT_2("event2"),EVENT_3("event3");
    private String event;
    private EventType(String someEvent) {

    }
    public String getEvent() {
        return this.event;
    }
}
