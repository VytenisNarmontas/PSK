// src/main/java/com/example/psk/entity/Course.java
package com.example.psk.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;            // match your BIGINT!

    // map this java field to the DB column "name"
    @Column(name = "name", nullable = false, length = 255)
    private String courseTitle;

    // inverse side of Student ↔ Course
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

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
