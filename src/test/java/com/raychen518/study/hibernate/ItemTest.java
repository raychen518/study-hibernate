package com.raychen518.study.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        // A SessionFactory is set up once for an application.
        // Note: Settings from the hibernate.cfg.xml file are used for the configuration.
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @After
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void test() {
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Date createdDate = new Date();
            for (int i = 0; i < 3; i++) {
                session.save(new Item(RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10), createdDate));
            }

            session.getTransaction().commit();
            session.close();
        }

        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Item> results = session.createQuery("from Item").list();
            for (Item result : results) {
                System.out.println(result.getId() + " : " + result.getField1() + " : " + result.getField2());
            }

            session.getTransaction().commit();
            session.close();
        }
    }

}
