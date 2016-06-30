package com.raychen518.study.hibernate;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class ProductC extends Product {

    @Field
    private String featureC1;

    @Field
    private String featureC2;

    public ProductC() {
    }

    public ProductC(Long serialNumber, String feature, String featureC1, String featureC2, Date createdDate) {
        this.serialNumber = serialNumber;
        this.feature = feature;
        this.featureC1 = featureC1;
        this.featureC2 = featureC2;
        this.createdDate = createdDate;
    }

    public String getFeatureC1() {
        return featureC1;
    }

    public void setFeatureC1(String featureC1) {
        this.featureC1 = featureC1;
    }

    public String getFeatureC2() {
        return featureC2;
    }

    public void setFeatureC2(String featureC2) {
        this.featureC2 = featureC2;
    }

    @Override
    public String toString() {
        return super.toString() + " ProductC [featureC1=" + featureC1 + ", featureC2=" + featureC2 + "]";
    }

}
