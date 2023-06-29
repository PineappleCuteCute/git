package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements InterfaceDAO<Question> {
	private static QuestionDao instance;

	public static QuestionDao getInstance() {
		if (QuestionDao.instance == null)
			QuestionDao.instance = new QuestionDao();
		return QuestionDao.instance;
	}

	@Override
	public List<Question> selectALl() {
		Transaction transaction = null;
		List<Question> questions = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Question.class);
			// selectAll Question object
			questions = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return questions;
	}

	@Override
	public Question selectById(int id) {
		Transaction transaction = null;
		Question question = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Question object
			question = session.get(Question.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return question;

	}

//	private void _increaseQuestionCount(Category category){
//		if(category.getParent() != null)
//			//Tăn questionCount len 1
//			// call this._increaseQuestionCount(parentCategory.id)
//		return;
//	}
	private void _increaseQuestionCount(Category category) {
		category.setQuestionCount(category.getQuestionCount() + 1);

		if (category.getParent() != null) {
			Category parentCategory = category.getParent();
			_increaseQuestionCount(parentCategory);
		}
	}

	@Override
	public void insert(Question question) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save Question object
			session.save(question);
			_increaseQuestionCount(question.getCategory());
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}

	}

	@Override
	public void update(Question question) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Question object
			session.saveOrUpdate(question);
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
		Question question = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			question = session.get(Question.class, id);
			// delete Question object
			session.delete(question);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}


}
