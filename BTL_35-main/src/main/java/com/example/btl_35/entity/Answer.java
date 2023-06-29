package com.example.btl_35.entity;

import javax.persistence.*;

@Entity(name = "Answer")
@Table(name = "tbl_Answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private Integer id;
	@Column(name = "choice", columnDefinition = "NVARCHAR(2000)")
	private String choice;
	@Column(name = "is_choice", columnDefinition = "bit")
	private boolean is_choice;

	@Column(name = "grade", columnDefinition = "FLOAT")
	private Double grade;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id", referencedColumnName = "question_id")
	private Question question;

	public Answer() {
	}

	public Answer(String choice, boolean is_choice, Double grade, Question question) {
		this.choice = choice;
		this.is_choice = is_choice;
		this.grade = grade;
		this.question = question;
	}

	public Answer(Integer id, String choice, boolean is_choice, Double grade, Question question) {
		this.id = id;
		this.choice = choice;
		this.is_choice = is_choice;
		this.grade = grade;
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isIs_choice() {
		return is_choice;
	}

	public void setIs_choice(boolean is_choice) {
		this.is_choice = is_choice;
	}

	public Answer(Integer id, String choice, boolean is_choice, Double grade) {
		this.id = id;
		this.choice = choice;
		this.is_choice = is_choice;
		this.grade = grade;
	}
}
