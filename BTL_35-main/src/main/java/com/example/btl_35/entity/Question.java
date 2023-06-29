package com.example.btl_35.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Question")
@Table(name = "tbl_Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Integer id;

	@Column(name = "name",nullable = false, columnDefinition = "NVARCHAR(200)")
	private String name;
	@Column(name = "text", nullable = false,columnDefinition ="NVARCHAR(2000)")
	private String text;
	@Column(name = "mark", nullable = false,columnDefinition = "INT")

	private Integer mark;

	// relational category
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	// relational quiz
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "quiz_question", joinColumns = { @JoinColumn(name = "question_id") }, inverseJoinColumns = {
			@JoinColumn(name = "quiz_id") })
	private Set<Quiz> listQuizQuestion = new HashSet<>();

	// relational awnser
	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
	private Set<Answer> answers = new HashSet<>();

	// relational media
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "media_id")
	private Media media;


	// constructor
	public Question() {

	}

	public Question(String name, String text, Integer mark) {
		this.name = name;
		this.text = text;
		this.mark = mark;

	}

	public Question(Integer id, String name, String text, Integer mark) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.mark = mark;

	}

	// getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Quiz> getListQuizQuestion() {
		return listQuizQuestion;
	}

	public void setListQuizQuestion(Set<Quiz> listQuizQuestion) {
		this.listQuizQuestion = listQuizQuestion;
	}

	@Override
	public String toString() {
		return "Question{" + "id=" + id + ", name='" + name + '\'' + ", text='" + text + '\'' + ", mark=" + mark + '}';
	}
}
