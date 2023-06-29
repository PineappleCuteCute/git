package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Quiz;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class QuizDao implements InterfaceDAO<Quiz> {
	private static QuizDao instance;

	public static QuizDao getInstance() {
		if (QuizDao.instance == null)
			QuizDao.instance = new QuizDao();
		return QuizDao.instance;
	}

	@Override
	public List<Quiz> selectALl() {
//		List<Quiz> quizList = new ArrayList<>();
		Transaction transaction = null;
		List<Quiz> quizList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Quiz.class);
			// selectAll Quiz object
			quizList = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return quizList;
	}

	@Override
	public Quiz selectById(int id) {
		Transaction transaction = null;
		Quiz quiz = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Quiz object
			quiz = session.get(Quiz.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return quiz;

	}

	@Override
	public void insert(Quiz quiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save Quiz object
			session.save(quiz);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Quiz quiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Quiz object
			session.saveOrUpdate(quiz);
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
		Quiz quiz = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			quiz = session.get(Quiz.class, id);
			// delete Quiz object
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
