package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import JPAUtil.JPAUtil;
import model.transactions;
import model.user;

public class transactionsDao {
	public List<transactions> findAll(int userID) {
        EntityManager em = JPAUtil.getManager();
        List<transactions> list = null;
        try {
            TypedQuery<transactions> query = em.createQuery("SELECT t FROM transactions t WHERE t.user.user_id = :userID", transactions.class);
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

    public boolean save(transactions t, int userID) {
        EntityManager em = JPAUtil.getManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            user user = em.find(user.class, userID);
            if (user == null) {
                throw new IllegalArgumentException("User không tồn tại với ID: " + userID);
            }
            t.setUser(user);
            em.persist(t);
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
            transactions transaction = em.find(transactions.class, id);
            if (transaction == null) {
                throw new IllegalArgumentException("Transaction không tồn tại với ID: " + id);
            }
            if (transaction.getUser().getUser_id() != userID) {
                throw new IllegalArgumentException("Transaction không thuộc về user với ID: " + userID);
            }
            em.remove(transaction);
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

    public boolean update(transactions t, int userID) {
        EntityManager em = JPAUtil.getManager();
        boolean success = false;
        try {
            em.getTransaction().begin();
            transactions existingTransaction = em.find(transactions.class, t.getTransaction_id());
            if (existingTransaction == null) {
                throw new IllegalArgumentException("Transaction không tồn tại với ID: " + t.getTransaction_id());
            }
            if (existingTransaction.getUser().getUser_id() != userID) {
                throw new IllegalArgumentException("Transaction không thuộc về user với ID: " + userID);
            }
            existingTransaction.setAmount(t.getAmount());
            existingTransaction.setDate(t.getDate());
            existingTransaction.setDescription(t.getDescription());
            existingTransaction.setCat(t.getCat());
            em.merge(existingTransaction);
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

    public List<transactions> findByMonthAndYear(int userID, int month, int year) {
        EntityManager em = JPAUtil.getManager();
        List<transactions> transactionsList = new ArrayList<>();
        try {
            String jpql = "SELECT t FROM transactions t WHERE t.user.user_id = :userID AND FUNCTION('MONTH', t.date) = :month AND FUNCTION('YEAR', t.date) = :year";
            TypedQuery<transactions> query = em.createQuery(jpql, transactions.class);
            query.setParameter("userID", userID);
            query.setParameter("month", month);
            query.setParameter("year", year);
            transactionsList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return transactionsList;
    }

}
