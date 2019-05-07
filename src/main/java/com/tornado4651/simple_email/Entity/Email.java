package com.tornado4651.simple_email.Entity;

import java.sql.Timestamp;

public class Email {

    int       id;
    Timestamp datetime;
    String    Receiver;
    String    subject;
    String    content;

    public Email() {

    }

    public Email(String receiver, String subject, String content) {
        Receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
