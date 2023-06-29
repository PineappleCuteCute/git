package com.example.btl_35.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_User_Quiz")
public class User_Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private double grade;

	// relational user
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user_q;

	// relational quiz
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id")
	private Quiz quiz_u;

	public User_Quiz() {
	}

	public User_Quiz(Integer id, Integer user_id, Integer quiz_id, double grade) {
		this.id = id;
		this.grade = grade;
	}

	public User_Quiz(Integer id, double grade, User user_q, Quiz quiz_u) {
		this.id = id;
		this.grade = grade;
		this.user_q = user_q;
		this.quiz_u = quiz_u;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public User getUser_q() {
		return user_q;
	}

	public void setUser_q(User user_q) {
		this.user_q = user_q;
	}

	public Quiz getQuiz_u() {
		return quiz_u;
	}

	public void setQuiz_u(Quiz quiz_u) {
		this.quiz_u = quiz_u;
	}

	@Override
	public String toString() {
		return "User_Quiz{" + "id=" + id + ", grade=" + grade + ", user_q=" + user_q + ", quiz_u=" + quiz_u + '}';
	}
}
