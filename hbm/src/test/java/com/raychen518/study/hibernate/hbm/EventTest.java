package com.raychen518.study.hibernate.hbm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

    private static final String QUERY_STRING_FIND_ALL_EVENTS = "from Event";

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        // Invoking the configure() method as follows reads settings from the
        // file hibernate.cfg.xml.
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
        List<Event> eventsAsInput = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            eventsAsInput.add(new Event(RandomStringUtils.randomAlphabetic(20), generateDate()));
        }

        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            for (Event event : eventsAsInput) {
                session.save(event);
            }

            session.getTransaction().commit();
            session.close();
        }

        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            List<Event> events = session.createQuery(QUERY_STRING_FIND_ALL_EVENTS, Event.class).getResultList();
            for (Event event : events) {
                System.out.println(event);
            }

            session.getTransaction().commit();
            session.close();

            assertNotNull(events);
            assertEquals(eventsAsInput.size(), events.size());
            Event eventAsInput;
            Event event;
            for (int i = 0; i < eventsAsInput.size(); i++) {
                eventAsInput = eventsAsInput.get(i);
                event = events.get(i);
                assertEquals(eventAsInput.getTitle(), event.getTitle());
                assertEquals(eventAsInput.getDate(), event.getDate());
            }
        }
    }

    private static Date generateDate() {
        return new Date(ThreadLocalRandom.current().nextLong(new Date().getTime()));
    }

}
