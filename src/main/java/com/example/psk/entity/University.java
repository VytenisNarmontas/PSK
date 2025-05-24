package com.example.psk.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // field name differs from column name: map uniName → name
    @Column(name = "name", nullable = false)
    private String uniName;

    // one university → many students
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    // --- constructors, getters, setters ---

    public University() {}

    public University(String uniName) {
        this.uniName = uniName;
    }

    public Integer getId() {
        return id;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
