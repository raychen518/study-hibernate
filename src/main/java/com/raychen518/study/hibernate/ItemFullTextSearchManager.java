package com.raychen518.study.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class ItemFullTextSearchManager {

    public static void main(String[] args) throws InterruptedException {
        ItemManager itemManager = new ItemManager();
        itemManager.deleteAllItems();
        itemManager.generateItemsForFullTextSearch();

        ItemFullTextSearchManager itemFullTextSearchManager = new ItemFullTextSearchManager();
        itemFullTextSearchManager.indexEntities();

        itemFullTextSearchManager.doGeneralSearch();
    }

    private void indexEntities() throws InterruptedException {
        System.out.println("System.getProperty(\"user.dir\"): " + System.getProperty("user.dir"));

        FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSessionFactory().getCurrentSession());
        fullTextSession.createIndexer().startAndWait();
    }

    private void doGeneralSearch() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction transaction = fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("field1").matching("f1aaaa").createQuery();
        org.hibernate.Query query = fullTextSession.createFullTextQuery(luceneQuery, Item.class);

        @SuppressWarnings("unchecked")
        List<Item> queryResults = query.list();
        for (Item item : queryResults) {
            System.out.println("item: " + item);
        }

        transaction.commit();
    }

}