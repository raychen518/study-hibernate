package com.raychen518.study.hibernate.entitymanager;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class represents an event.
 */
@Entity
@Table(name = "EVENTS")
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

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
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
