package com.brunon.takeaway.model;

public enum OrderStatus {
    NEW,
    IN_PROGRESS,
    FINISHED;

    public static OrderStatus nextStatus(OrderStatus status) {
        if (status == NEW) {
            return IN_PROGRESS;
        } else if (status == IN_PROGRESS) {
            return FINISHED;
        } else {
            throw new IllegalArgumentException("There is no status for this value");
        }
    }
}
