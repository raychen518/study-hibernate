package com.raychen518.study.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

public class ItemManager {

    public static void main(String[] args) {
        ItemManager itemManager = new ItemManager();

        Serializable itemIdentifier = itemManager.saveItem();
        System.out.println("itemIdentifier: " + itemIdentifier);

        Item item = itemManager.findItem(itemIdentifier);
        System.out.println("item: " + item);

        itemManager.updateItem(item);

        System.out.println("itemManager.findItem(itemIdentifier): " + itemManager.findItem(itemIdentifier));

        itemManager.deleteItem(item);

        System.out.println("itemManager.findItem(itemIdentifier): " + itemManager.findItem(itemIdentifier));

        itemManager.generateRandomItems();

        List<Item> items = itemManager.findAllItems();
        for (Item theItem : items) {
            System.out.println("theItem: " + theItem);
        }

        itemManager.deleteAllItems();

        HibernateUtil.getSessionFactory().close();
    }

    public Item findItem(Serializable itemIdentifier) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Item item = session.get(Item.class, itemIdentifier);

        session.getTransaction().commit();

        return item;
    }

    public List<Item> findAllItems() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Item> items = session.createQuery("from Item").list();

        session.getTransaction().commit();

        return items;
    }

    public Serializable saveItem() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Item item = new Item();
        item.setField1(RandomStringUtils.randomAlphanumeric(10));
        item.setField2(RandomStringUtils.randomAlphanumeric(10));
        item.setCreatedDate(new Date());
        Serializable itemIdentifier = session.save(item);

        session.getTransaction().commit();

        return itemIdentifier;
    }

    public void updateItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        item.setField1(RandomStringUtils.randomAlphanumeric(15));
        item.setField2(RandomStringUtils.randomAlphanumeric(15));
        item.setModifiedDate(new Date());
        session.update(item);

        session.getTransaction().commit();
    }

    public void deleteItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(item);

        session.getTransaction().commit();
    }

    public void deleteAllItems() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Item> items = session.createQuery("from Item").list();

        for (Item item : items) {
            session.delete(item);
        }

        session.getTransaction().commit();
    }

    public void generateRandomItems() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for (int i = 0; i < 5; i++) {
            session.save(new Item(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10),
                    new Date()));
        }

        session.getTransaction().commit();
    }

    public void generateItemsForFullTextSearch() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Field1 Field2
        // ---------------------------------------------------------------------
        // f1aaaa f2aaaa
        // f1bbbb f2aaaa
        // f1aaaa f2aaaa
        // f1cccc f2aaaa
        // f1aaaa f2aaaa
        session.save(new Item("f1aaaa", "f2aaaa", new Date()));
        session.save(new Item("f1bbbb", "f2aaaa", new Date()));
        session.save(new Item("f1aaaa", "f2aaaa", new Date()));
        session.save(new Item("f1cccc", "f2aaaa", new Date()));
        session.save(new Item("f1aaaa", "f2aaaa", new Date()));

        session.getTransaction().commit();
    }

}