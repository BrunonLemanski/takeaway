package com.brunon.takeaway.model;

import lombok.Data;

@Data
public class Message {

    private String status;
    private String text;

    public Message(String status, String text) {
        this.status = status;
        this.text = text;
    }
}
