package com.example.btl_35.dao;

import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Media;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MediaDao implements InterfaceDAO<Media> {
	private static MediaDao instance;

	public static MediaDao getInstance() {
		if (MediaDao.instance == null)
			MediaDao.instance = new MediaDao();
		return MediaDao.instance;
	}

	@Override
	public List<Media> selectALl() {
		Transaction transaction = null;
		List<Media> mediaList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Media.class);
			// selectAll Media object
			mediaList = criteria.list();
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return mediaList;
	}

	@Override
	public Media selectById(int id) {
		Transaction transaction = null;
		Media media = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// selectById Media object
			media = session.get(Media.class, id);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return media;

	}

	@Override
	public void insert(Media media) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// save Media object
			session.save(media);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Media media) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			// update Media object
			session.saveOrUpdate(media);
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
		Media media = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();
			media = session.get(Media.class, id);
			// delete Media object
			session.delete(media);
			// commit the transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

}
