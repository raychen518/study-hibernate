package com.raychen518.study.hibernate;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class ProductB extends Product {

    @Field
    private String featureB1;

    @Field
    private String featureB2;

    public ProductB() {
    }

    public ProductB(String feature, String featureB1, String featureB2, Date createdDate) {
        this.feature = feature;
        this.featureB1 = featureB1;
        this.featureB2 = featureB2;
        this.createdDate = createdDate;
    }

    public String getFeatureB1() {
        return featureB1;
    }

    public void setFeatureB1(String featureB1) {
        this.featureB1 = featureB1;
    }

    public String getFeatureB2() {
        return featureB2;
    }

    public void setFeatureB2(String featureB2) {
        this.featureB2 = featureB2;
    }

    @Override
    public String toString() {
        return super.toString() + " ProductB [featureB1=" + featureB1 + ", featureB2=" + featureB2 + "]";
    }

}
