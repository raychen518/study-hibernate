package com.raychen518.study.hibernate;

import java.util.Date;

import org.hibernate.Session;

public class EventManager {

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        eventManager.createAndStoreEvent("My Event", new Date());

        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event event = new Event();
        event.setTitle(title);
        event.setDate(date);
        session.save(event);

        session.getTransaction().commit();
    }

}