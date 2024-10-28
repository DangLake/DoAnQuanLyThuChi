package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import JPAUtil.JPAUtil;
import model.budgets;
import model.user;

public class budgetsDao {
	 public List<budgets> findAll(int userID) {
	        EntityManager em = JPAUtil.getManager();
	        List<budgets> list = null;
	        try {
	            TypedQuery<budgets> query = em.createQuery("SELECT b FROM budgets b WHERE b.user.user_id = :userID", budgets.class);
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

	    public boolean save(budgets b, int userID) {
	        EntityManager em = JPAUtil.getManager();
	        boolean success = false;
	        try {
	            em.getTransaction().begin();
	            user user = em.find(user.class, userID);
	            if (user == null) {
	                throw new IllegalArgumentException("User không tồn tại với ID: " + userID);
	            }
	            b.setUser(user);
	            em.persist(b);
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

	    public boolean delete(Integer id, int userID) {
	        EntityManager em = JPAUtil.getManager();
	        boolean success = false;
	        try {
	            em.getTransaction().begin();
	            budgets budget = em.find(budgets.class, id);
	            if (budget == null) {
	                throw new IllegalArgumentException("Budget không tồn tại với ID: " + id);
	            }
	            if (budget.getUser().getUser_id() != userID) {
	                throw new IllegalArgumentException("Budget không thuộc về user với ID: " + userID);
	            }
	            em.remove(budget);
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

	    public boolean update(budgets b, int userID) {
	        EntityManager em = JPAUtil.getManager();
	        boolean success = false;
	        try {
	            em.getTransaction().begin();
	            budgets existingBudget = em.find(budgets.class, b.getBudget_id());
	            if (existingBudget == null) {
	                throw new IllegalArgumentException("Budget không tồn tại với ID: " + b.getBudget_id());
	            }
	            if (existingBudget.getUser().getUser_id() != userID) {
	                throw new IllegalArgumentException("Budget không thuộc về user với ID: " + userID);
	            }
	            existingBudget.setAmount(b.getAmount());
	            existingBudget.setStart_date(b.getStart_date());
	            existingBudget.setEnd_date(b.getEnd_date());
	            existingBudget.setCat(b.getCat());
	            em.merge(existingBudget);
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
