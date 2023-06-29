package com.example.btl_35.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "name", unique = true, columnDefinition = "NVARCHAR(200)")
	private String name;
	@Column(name = "passWord", columnDefinition = "NVARCHAR(200)")
	private String passWord;
	@Column(name = "fullName", columnDefinition = "NVARCHAR(200)")
	private String fullName;

	// relation user_quizs
	@OneToMany(mappedBy = "user_q", cascade = CascadeType.ALL)
	private Set<User_Quiz> user_quizs = new HashSet<>();

	public User() {
	}

	public User(String name, String passWord, String fullName) {
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.fullName = fullName;
	}

	public User(Integer id, String name, String passWord, String fullName) {
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.fullName = fullName;
	}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Set<User_Quiz> getUser_quizs() {
		return user_quizs;
	}

	public void setUser_quizs(Set<User_Quiz> user_quizs) {
		this.user_quizs = user_quizs;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", passWord='" + passWord + '\'' + ", fullName='"
				+ fullName + '\'' + '}';
	}
}
