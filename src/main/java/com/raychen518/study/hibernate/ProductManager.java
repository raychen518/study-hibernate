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
        productManager.deleteAllProducts();
        productManager.generateSomeProducts();
        productManager.searchProducts();
    }

    private void indexAllProducts() throws InterruptedException {
        FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSessionFactory().getCurrentSession());
        fullTextSession.createIndexer().startAndWait();
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

        session.save(new ProductA("feature001", "featureA1001", "featureA2001", new Date()));
        session.save(new ProductA("feature002", "featureA1002", "featureA2002", new Date()));
        session.save(new ProductA("feature003", "featureA1003", "featureA2003", new Date()));

        session.save(new ProductB("feature001", "featureB1001", "featureB2001", new Date()));
        session.save(new ProductB("feature002", "featureB1002", "featureB2002", new Date()));
        session.save(new ProductB("feature003", "featureB1003", "featureB2003", new Date()));

        session.save(new ProductC("feature001", "featureC1001", "featureC2001", new Date()));
        session.save(new ProductC("feature002", "featureC1002", "featureC2002", new Date()));
        session.save(new ProductC("feature003", "featureC1003", "featureC2003", new Date()));

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

}