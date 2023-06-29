package com.example.btl_35;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.dao.QuizDao;
import com.example.btl_35.database.HibernateUtil;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Set;

public class Test extends Application {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
//                Question c1 = QuestionDao.getInstance().selectById(1);
//                Question c2 = QuestionDao.getInstance().selectById(2);
//                Question c3 = QuestionDao.getInstance().selectById(3);
//                Quiz quiz = session.get(Quiz.class, 2);
//                Hibernate.initialize(quiz.getListQuestionQuiz());
//                Quiz dethi = QuizDao.getInstance().selectById(2);
//                Set<Question> questions = dethi.getListQuestionQuiz();
//
//                for(Question question: questions ){
//                    System.out.println(question.getId());
//                }

                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}