package com.raychen518.study.hibernate;

import java.util.Date;

public class Event {

    private Long id;
    private String title;
    private Date createdDate;

    public Event() {
    }

    public Event(String title, Date createdDate) {
        this.title = title;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
