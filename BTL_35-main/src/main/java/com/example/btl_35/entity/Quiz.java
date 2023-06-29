package com.example.btl_35.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Quiz")
@Table(name = "tbl_Quiz")
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private Integer id;

	@Column(name = "name", unique = true,nullable = false, columnDefinition = "NVARCHAR(200)")

	private String name;
	@Column(name = "description", columnDefinition = "NVARCHAR(2000)")
	private String description;
	@Column(name = "timeOpen", columnDefinition = "DATETIME")
	private LocalDateTime timeOpen;
	@Column(name = "timeClose", columnDefinition = "DATETIME")
	private LocalDateTime timeClose;
	@Column(name = "limit_Time", columnDefinition = "TIME")
	private LocalTime limit_Time;
	// relational question
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "quiz_question", joinColumns = { @JoinColumn(name = "quiz_id") }, inverseJoinColumns = {
			@JoinColumn(name = "question_id") })
	private Set<Question> listQuestionQuiz = new HashSet<Question>();

	// relational quiz_user
	@OneToMany(mappedBy = "quiz_u", cascade = CascadeType.ALL)
	private Set<User_Quiz> quiz_user = new HashSet<>();

	public Quiz() {

	}



//	public Quiz(Integer id, String name, String description, Date timeOpen, Date timeClose, Time limit_Time,
//			Set<Question> listQuestionQuiz, Set<User_Quiz> quiz_user) {
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.timeOpen = timeOpen;
//		this.timeClose = timeClose;
//		this.limit_Time = limit_Time;
//		this.listQuestionQuiz = listQuestionQuiz;
//		this.quiz_user = quiz_user;
//	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


//	public Quiz(Integer id, String name, String description, LocalDateTime timeOpen, LocalDateTime timeClose, Time limit_Time) {
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.timeOpen = timeOpen;
//		this.timeClose = timeClose;
//		this.limit_Time = limit_Time;
//	}


	public Quiz(Integer id, String name, String description, LocalDateTime timeOpen, LocalDateTime timeClose, LocalTime limit_Time) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.limit_Time = limit_Time;
	}

	public LocalDateTime getTimeOpen() {
		return timeOpen;
	}

	public LocalDateTime getTimeClose() {
		return timeClose;
	}

	public void setTimeOpen(LocalDateTime timeOpen) {
		this.timeOpen = timeOpen;


	}

	public void setTimeClose(LocalDateTime timeClose) {
		this.timeClose = timeClose;
	}
	//	public Date getTimeOpen() {
//		return timeOpen;
//	}
//
//	public void setTimeOpen(Date timeOpen) {
//		this.timeOpen = timeOpen;
//	}
//
//	public Date getTimeClose() {
//		return timeClose;
//	}
//
//	public void setTimeClose(Date timeClose) {
//		this.timeClose = timeClose;
//	}

//	public Time getLimit_Time() {
//		return limit_Time;
//	}
//
//	public void setLimit_Time(Time limit_Time) {
//		this.limit_Time = limit_Time;
//	}


	public LocalTime getLimit_Time() {
		return limit_Time;
	}

	public void setLimit_Time(LocalTime limit_Time) {
		this.limit_Time = limit_Time;
	}

	public Set<Question> getListQuestionQuiz() {
		return listQuestionQuiz;
	}

	public void setListQuestionQuiz(Set<Question> listQuestionQuiz) {
		this.listQuestionQuiz = listQuestionQuiz;
	}

	public Set<User_Quiz> getQuiz_user() {
		return quiz_user;
	}

	public void setQuiz_user(Set<User_Quiz> quiz_user) {
		this.quiz_user = quiz_user;
	}
}
