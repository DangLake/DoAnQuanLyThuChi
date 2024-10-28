package dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import JPAUtil.JPAUtil;
import model.categories;
import model.user;
public class categoriesDao {
	public List<categories> findAll(int userID) {
	    EntityManager em = JPAUtil.getManager();
	    List<categories> list = null;
	    try {
	        TypedQuery<categories> query = em.createQuery("SELECT c FROM categories c WHERE c.user.user_id = :userID", categories.class);
	        query.setParameter("userID", userID);
	        list = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	    return list;
	}


    // Save a new category for a specific user
    public boolean save(categories c, int userID) {
        EntityManager em = JPAUtil.getManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            user user = em.find(user.class, userID);
            if (user == null) {
                throw new IllegalArgumentException("User không tồn tại với ID: " + userID);
            }
            c.setUser(user);
            em.persist(c);
            em.getTransaction().commit();
            success = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return success;
    }

    // Delete a category by ID and user ID
    public boolean delete(Integer id, int userID) {
        EntityManager em = JPAUtil.getManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            categories category = em.find(categories.class, id);
            if (category == null) {
                throw new IllegalArgumentException("Category không tồn tại với ID: " + id);
            }
            if (category.getUser().getUser_id() != userID) {
                throw new IllegalArgumentException("Category không thuộc về user với ID: " + userID);
            }
            em.remove(category);
            em.getTransaction().commit();
            success = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return success;
    }

    // Update a category for a specific user
    public boolean update(categories t, int userID) {
        EntityManager em = JPAUtil.getManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            categories existingCategory = em.find(categories.class, t.getCategory_id());
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category không tồn tại với ID: " + t.getCategory_id());
            }
            if (existingCategory.getUser().getUser_id() != userID) {
                throw new IllegalArgumentException("Category không thuộc về user với ID: " + userID);
            }
            existingCategory.setName(t.getName());
            existingCategory.setType(t.isType());
            em.merge(existingCategory);
            em.getTransaction().commit();
            success = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return success;
    }
}
