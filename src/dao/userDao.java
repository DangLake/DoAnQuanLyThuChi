package dao;
import javax.persistence.*;

import JPAUtil.JPAUtil;

import model.user;

import java.util.*;
public class userDao {
	public List<user> findAll(){
		EntityManager em=JPAUtil.getManager();
		List<user> list=em.createQuery("from user").getResultList();
		em.close();
		return list;
	}
	public boolean save(user t) {
		EntityManager em=JPAUtil.getManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		boolean kq=true;
		em.close();
		return kq;
	}
	public user findBymail(String mail) {
		EntityManager em=JPAUtil.getManager();
		try {
	        TypedQuery<user> query = em.createQuery("SELECT u FROM user u WHERE u.email = :mail", user.class);
	        query.setParameter("mail", mail);
	        return query.getSingleResult(); 
	    } catch (NoResultException e) {
	        return null;
	    } finally {
	        em.close();
	    }
	}
	public user findByPass(String pass,String email) {
		EntityManager em=JPAUtil.getManager();
		try {
            TypedQuery<user> query = em.createQuery("SELECT u FROM user u WHERE u.password_hash = :pass and u.email = :email", user.class);
            query.setParameter("pass", pass);
            query.setParameter("email", email); 
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
	}
	public int getUserIDFromDatabase(String email) {
	    EntityManager em = JPAUtil.getManager();
	    try {
	        TypedQuery<Integer> query = em.createQuery("SELECT u.user_id FROM user u WHERE u.email = :email", Integer.class);
	        query.setParameter("email", email);
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return 0;
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	}
	public boolean update(user t) {
		EntityManager em=JPAUtil.getManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		boolean kq=true;
		em.close();
		return kq;
	}
}
