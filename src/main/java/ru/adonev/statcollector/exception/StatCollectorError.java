/*
 * Copyright (c) 2023.
 * github.com/EgorAdonev
 */

package ru.adonev.statcollector.exception;

public class StatCollectorError {
    private int statusCode;
    private String message;

    public StatCollectorError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public StatCollectorError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
