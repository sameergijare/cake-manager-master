package com.waracle.cakemgr;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

import static com.waracle.cakemgr.HibernateUtil.getSessionFactory;

public class CakeDao {

    public List<CakeEntity> getCakes() {
        try (final CloseableSession closeableSession = new CloseableSession(getSessionFactory().openSession())) {
            return closeableSession.getSession().createCriteria(CakeEntity.class).list();
        }
    }

    public void addCake(CakeEntity cakeEntity) {
    	System.out.println("Started Adding Cake.....");
        try {
            try (final CloseableSession closeableSession = new CloseableSession(getSessionFactory().openSession())) {
                Session session = closeableSession.getSession();
                session.beginTransaction();
                session.persist(cakeEntity);
                session.getTransaction().commit();
            }
        } catch (ConstraintViolationException cve) {
            System.out.println("Failed to save cake due to DB contraint violation: " + cve.getMessage());
        }
        System.out.println("Completed Adding Cake.....");
    }
}
