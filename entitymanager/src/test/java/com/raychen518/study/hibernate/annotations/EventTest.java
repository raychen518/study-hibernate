package com.raychen518.study.hibernate.annotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.raychen518.study.hibernate.entitymanager.Event;

public class EventTest {

    private static final String QUERY_STRING_FIND_ALL_EVENTS = "from Event";

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("com.raychen518.study.hibernate.entitymanager.jpa");
    }

    @After
    public void tearDown() {
        entityManagerFactory.close();
    }

    @Test
    public void test() {
        List<Event> eventsAsInput = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            eventsAsInput.add(new Event(RandomStringUtils.randomAlphabetic(20), generateDate()));
        }

        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            for (Event event : eventsAsInput) {
                entityManager.persist(event);
            }

            entityManager.getTransaction().commit();
            entityManager.close();
        }

        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Event> events = entityManager.createQuery(QUERY_STRING_FIND_ALL_EVENTS, Event.class).getResultList();
            for (Event event : events) {
                System.out.println(event);
            }

            entityManager.getTransaction().commit();
            entityManager.close();

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
