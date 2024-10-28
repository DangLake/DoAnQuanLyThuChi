package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import JPAUtil.JPAUtil;
import model.transactions;

public class GetThuChi {
	public int getTienThu(int userID) {
	    EntityManager em = JPAUtil.getManager();
	    try {
	        TypedQuery<Long> query = em.createQuery(
	            "SELECT COALESCE(SUM(tr.amount), 0) FROM transactions tr " +
	            "JOIN tr.cat cate " +
	            "WHERE tr.user.user_id = :userID AND cate.type = false", Long.class);
	        query.setParameter("userID", userID);
	        return query.getSingleResult().intValue();
	    } catch (NoResultException e) {
	        return 0;
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	}

	public int getTienChi(int userID) {
	    EntityManager em = JPAUtil.getManager();
	    try {
	        TypedQuery<Long> query = em.createQuery(
	            "SELECT COALESCE(SUM(tr.amount), 0) FROM transactions tr " +
	            "JOIN tr.cat cate " +
	            "WHERE tr.user.user_id = :userID AND cate.type = true", Long.class);
	        query.setParameter("userID", userID);
	        return query.getSingleResult().intValue();
	    } catch (NoResultException e) {
	        return 0;
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	}
	public int layTongChitheoCate(int userID, int categoryID) {
	    EntityManager em = JPAUtil.getManager();
	    try {
	        TypedQuery<Long> query = em.createQuery(
	            "SELECT COALESCE(SUM(tr.amount), 0) FROM transactions tr " +
	            "WHERE tr.user.user_id = :userID AND tr.cat.category_id = :categoryID AND tr.cat.type = true", Long.class);
	        query.setParameter("userID", userID);
	        query.setParameter("categoryID", categoryID);
	        return query.getSingleResult().intValue();
	    } catch (NoResultException e) {
	        return 0;
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	}
	public int LayNganSachCat(int categoryID) {
	    EntityManager em = JPAUtil.getManager();
	    try {
	        TypedQuery<Integer> query = em.createQuery(
	            "SELECT b.amount FROM budgets b WHERE b.cat.category_id = :categoryID", Integer.class);
	        query.setParameter("categoryID", categoryID);
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return 0;
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	}

	public List<transactions> getTransactionsByYear(int userID, int year) {
        EntityManager em = JPAUtil.getManager();
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = cal.getTime();
            
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DAY_OF_MONTH, 31);
            Date endDate = cal.getTime();
            
            TypedQuery<transactions> query = em.createQuery(
                "SELECT tr FROM transactions tr " +
                "WHERE tr.user.user_id = :userID AND tr.date >= :startDate AND tr.date <= :endDate", transactions.class);
            query.setParameter("userID", userID);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
