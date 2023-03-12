/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.exception;

import ru.adonev.statcollector.model.Event;

public class EventException extends RuntimeException {
    public EventException(String message) {
        super(message);
    }
}
