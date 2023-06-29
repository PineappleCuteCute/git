package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.jdbc.Work;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryDao implements InterfaceDAO<Category> {

	private static CategoryDao instance;

	public static CategoryDao getInstance() {
		if (CategoryDao.instance == null)
			CategoryDao.instance = new CategoryDao();
		return CategoryDao.instance;
	}

	@Override
	public List<Category> selectALl() {
		Transaction transaction = null;
		List<Category> categories = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Category.class);
			// selectAllCategory object
			categories = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return categories;
	}

	@Override
	public Category selectById(int id) {
		Transaction transaction = null;
		Category category = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Category object
			category = session.get(Category.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return category;

	}

	@Override
	public void insert(Category category) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
//			session.doWork(new Work() {
//				@Override
//				public void execute(Connection connection) throws SQLException {
//					String sql = "SET IDENTITY_INSERT tbl_Category ON;";
//					try (Statement statement = connection.createStatement()) {
//						statement.execute(sql);
//					}
//				}
//			});
			// save Category object
			session.save(category);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Category category) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Category object
			session.saveOrUpdate(category);
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
		Category category = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			category = session.get(Category.class, id);
			// delete Category object
			session.delete(category);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	// Khi không nhấn vào

	public List<Question> getQuestionByCategoryID(int categoryID) {
		Transaction transaction = null;
		List<Question> questions = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Query query = session.createQuery("FROM Question Q WHERE Q.category.id = :categoryID");
			query.setParameter("categoryID", categoryID);
			questions = query.getResultList();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return questions;
	}

	public List<Question> getAllQuestionsByCategoryId(int categoryId) {
		Transaction transaction = null;
		List<Question> questions = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Query query = session.createQuery("SELECT Q FROM Question Q JOIN Q.category C WHERE Q.category.id = :categoryId OR C.parent.id = :categoryId");

			query.setParameter("categoryId", categoryId);
			questions = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return questions;
	}




}
