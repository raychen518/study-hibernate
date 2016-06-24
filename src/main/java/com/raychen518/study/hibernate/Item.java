package com.raychen518.study.hibernate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class Item {

    @Id
    private Long id;

    @Field
    private String field1;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String field2;

    private Date createdDate;
    private Date modifiedDate;

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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", field1=" + field1 + ", field2=" + field2 + ", createdDate=" + createdDate
                + ", modifiedDate=" + modifiedDate + "]";
    }

}
