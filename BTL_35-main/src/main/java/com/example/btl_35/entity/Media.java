package com.example.btl_35.entity;

import javax.persistence.*;

@Entity(name = "Media")
@Table(name = "tbl_Media")
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "media_id")
	private Integer id;
	@Column(name = "media")
	private String url;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "question_id", referencedColumnName = "question_id")
//	private Question questions;
	@OneToOne(mappedBy = "media",cascade = CascadeType.ALL)
	private Question question;

	public Media() {
	}

	public Media(Integer id, String url, Question question) {
		this.id = id;
		this.url = url;
		this.question = question;
	}

	public Media(Integer id, String url) {
		this.id = id;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
