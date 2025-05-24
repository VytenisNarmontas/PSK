package com.example.psk.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // map courseTitle → title
    @Column(name = "title", nullable = false)
    private String courseTitle;

    // inverse side of Student ↔ Course
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    // --- constructors, getters, setters ---

    public Course() {}

    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getId() {
        return id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
