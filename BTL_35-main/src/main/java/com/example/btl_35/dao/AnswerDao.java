package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Answer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AnswerDao implements InterfaceDAO<Answer> {
	private static AnswerDao instance;

	public static AnswerDao getInstance() {
		if (AnswerDao.instance == null)
			AnswerDao.instance = new AnswerDao();
		return AnswerDao.instance;
	}

	@Override
	public List<Answer> selectALl() {
		Transaction transaction = null;
		List<Answer> answers = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Answer.class);
			// selectAll Answer object
			answers = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return answers;
	}

	@Override
	public Answer selectById(int id) {
		Transaction transaction = null;
		Answer answer = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Answer object
			answer = session.get(Answer.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return answer;
	}

	@Override
	public void insert(Answer answer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save Answer object
			session.save(answer);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Answer answer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Awnser object
			session.saveOrUpdate(answer);
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
		Answer answer = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			answer = session.get(Answer.class, id);
			// delete Answer object
			session.delete(answer);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
}
