package com.example.btl_35.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Category")
@Table(name = "tbl_Category")
public class Category {

    @Id
    @Column(name = "category_id",unique = true)
    private Integer id;

    @Column(name = "name",nullable = false, unique = true, columnDefinition = "NVARCHAR(200)")
    private String name;
    @Column(name = "info", columnDefinition = "NVARCHAR(2000)")
    private String info;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;
    @Column(name = "questionCount")
    private int questionCount;

    // relational question
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();


    public Category() {
    }





    public Category(String name, String info, Category parent, int questionCount) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.parent = parent;
        this.questionCount = questionCount;
    }

    public Category(Integer id, String name, String info, Category parent, int questionCount) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.parent = parent;
        this.questionCount = questionCount;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Question> getquestions() {
        return questions;
    }

    public void setquestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", parent=" + parent +
                ", questionCount=" + questionCount +
                '}';
    }
}
