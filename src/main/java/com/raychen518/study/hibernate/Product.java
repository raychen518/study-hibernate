package com.raychen518.study.hibernate;

import java.util.Date;

import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

public abstract class Product {

    @Id
    protected Long id;

    @DocumentId(name = "id")
    @Field
    protected Long serialNumber;

    @Field
    protected String name;

    @Field
    protected String feature;

    protected Date createdDate;

    protected Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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
        return "Product [id=" + id + ", serialNumber=" + serialNumber + ", name=" + name + ", feature=" + feature
                + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
    }

}
