package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDao implements InterfaceDAO<User> {
	private static UserDao instance;

	public static UserDao getInstance() {
		if (UserDao.instance == null)
			UserDao.instance = new UserDao();
		return UserDao.instance;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<User> selectALl() {
		Transaction transaction = null;
		List<User> users = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			// selectAll Users object
			users = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return users;
	}

	@Override
	public User selectById(int id) {
		Transaction transaction = null;
		User user = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById User object
			user = session.get(User.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return user;

	}

	@Override
	public void insert(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save User object
			session.save(user);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update User object
			session.saveOrUpdate(user);
			// commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void delete(int id) {
		Transaction transaction = null;
		User user = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			user = session.get(User.class, id);
			// delete User object
			session.delete(user);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
}
