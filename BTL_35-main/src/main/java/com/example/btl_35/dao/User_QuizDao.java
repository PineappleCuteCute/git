package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.User_Quiz;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class User_QuizDao implements InterfaceDAO<User_Quiz> {


	private static User_QuizDao instance;

	public static User_QuizDao getInstance() {
		if (User_QuizDao.instance == null)
			User_QuizDao.instance = new User_QuizDao();
		return User_QuizDao.instance;
	}
	@Override
	public List<User_Quiz> selectALl() {
		Transaction transaction = null;
		List<User_Quiz> userQuizs = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User_Quiz.class);
			// selectAll User_Quiz object
			userQuizs = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return userQuizs;
	}

	@Override
	public User_Quiz selectById(int id) {
		Transaction transaction = null;
		User_Quiz user_quiz = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById User_Quiz object
			user_quiz = session.get(User_Quiz.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return user_quiz;

	}

	@Override
	public void insert(User_Quiz userQuiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save User_Quiz object
			session.save(userQuiz);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(User_Quiz userQuiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update User_Quiz object
			session.saveOrUpdate(userQuiz);
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
		User_Quiz quiz = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			quiz = session.get(User_Quiz.class, id);
			// delete User_Quiz object
			session.delete(quiz);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
