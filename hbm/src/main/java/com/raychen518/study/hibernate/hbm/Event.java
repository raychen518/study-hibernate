package com.raychen518.study.hibernate.hbm;

import java.util.Date;

/**
 * This class represents an event.
 */
public class Event {

    private Long id;
    private String title;
    private Date date;

    public Event() {
    }

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(Event.class.getSimpleName() + Constants.SPACE + Constants.BRACKET_LFET);
        result.append("id" + Constants.EQUAL_SIGN + id);
        result.append(Constants.COMMA + Constants.SPACE);
        result.append("title" + Constants.EQUAL_SIGN + title);
        result.append(Constants.COMMA + Constants.SPACE);
        result.append("date" + Constants.EQUAL_SIGN + date);
        result.append(Constants.BRACKET_RIGHT);

        return result.toString();
    }

}
