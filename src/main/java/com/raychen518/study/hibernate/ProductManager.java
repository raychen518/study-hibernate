package com.raychen518.study.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class ProductManager {

    public static void main(String[] args) throws InterruptedException {
        ProductManager productManager = new ProductManager();
        productManager.indexAllProducts();
        // productManager.deleteAllProducts();
        productManager.generateSomeProducts();
        // productManager.searchProducts();
        // productManager.searchProducts1();
    }

    private void indexAllProducts() throws InterruptedException {
        FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSessionFactory().getCurrentSession());
        fullTextSession.createIndexer(ProductA.class).startAndWait();
    }

    public void deleteAllProducts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Product> results = session.createQuery("from Product").list();

        for (Product result : results) {
            session.delete(result);
        }

        session.getTransaction().commit();
    }

    public void generateSomeProducts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Date createdDate = new Date();

        session.save(new ProductA(CommonsUtil.generateRandomNumber(), "feature001", "featureA1001", "featureA2001",
                createdDate));
        session.save(new ProductA(CommonsUtil.generateRandomNumber(), "feature002", "featureA1002", "featureA2002",
                createdDate));
        session.save(new ProductA(CommonsUtil.generateRandomNumber(), "feature003", "featureA1003", "featureA2003",
                createdDate));

        session.save(new ProductB(CommonsUtil.generateRandomNumber(), "feature001", "featureB1001", "featureB2001",
                createdDate));
        session.save(new ProductB(CommonsUtil.generateRandomNumber(), "feature002", "featureB1002", "featureB2002",
                createdDate));
        session.save(new ProductB(CommonsUtil.generateRandomNumber(), "feature003", "featureB1003", "featureB2003",
                createdDate));

        session.save(new ProductC(CommonsUtil.generateRandomNumber(), "feature001", "featureC1001", "featureC2001",
                createdDate));
        session.save(new ProductC(CommonsUtil.generateRandomNumber(), "feature002", "featureC1002", "featureC2002",
                createdDate));
        session.save(new ProductC(CommonsUtil.generateRandomNumber(), "feature003", "featureC1003", "featureC2003",
                createdDate));

        Long serialNumber = 666L;
        session.save(new ProductA(serialNumber, "feature004", "featureA1004", "featureA2004", createdDate));
        session.save(new ProductA(serialNumber, "feature004", "featureA1004", "featureA2004", createdDate));
        session.save(new ProductA(serialNumber, "feature004", "featureA1004", "featureA2004", createdDate));
        session.save(new ProductA(serialNumber, "feature004", "featureA1004", "featureA2004", createdDate));

        session.getTransaction().commit();
    }

    private void searchProducts() {
        FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSessionFactory().getCurrentSession());
        fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ProductA.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("feature").matching("feature002").createQuery();

        // Set the 2nd method parameter using "Product.class" to get products of the types ProductA, ProductB and ProductC.
        // Set the 2nd method parameter using "ProductA.class" to get products of the types ProductA.
        Query query = fullTextSession.createFullTextQuery(luceneQuery, ProductA.class);

        @SuppressWarnings("unchecked")
        List<Product> queryResults = query.list();
        for (Product queryResult : queryResults) {
            System.out.println("queryResult: " + queryResult);
        }

        fullTextSession.getTransaction().commit();
    }

    private void searchProducts1() {
        FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSessionFactory().getCurrentSession());
        fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ProductA.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("serialNumber").matching(123L).createQuery();

        Query query = fullTextSession.createFullTextQuery(luceneQuery, ProductA.class);

        @SuppressWarnings("unchecked")
        List<Product> queryResults = query.list();
        for (Product queryResult : queryResults) {
            System.out.println("queryResult: " + queryResult);
        }

        fullTextSession.getTransaction().commit();
    }

}