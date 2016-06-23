package com.raychen518.study.hibernate;

import java.util.Date;

public class Item {

    private Long id;
    private String field1;
    private String field2;
    private Date createdDate;

    public Item() {
    }

    public Item(String field1, String field2, Date createdDate) {
        this.field1 = field1;
        this.field2 = field2;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
