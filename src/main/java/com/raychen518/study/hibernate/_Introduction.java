package com.raychen518.study.hibernate;

/**
 * <pre>
 * - Hibernate can access public/protected/private accessor methods and fields.
 *   Hibernate can also access private constructors, but public/package constructors should be better.
 * 
 * - All persistent classes must have the default no-argument constructor,
 *   because Hibernate creates objects using the Reflection.
 *   
 * - Hibernate knows how to load and store objects of the persistent classes, by the Hibernate Mapping file.
 *   The Hibernate Mapping file tells Hibernate what tables and columns in the database should be used.
 * </pre>
 */
public class _Introduction {

    public static void main(String[] args) {
    }

}
