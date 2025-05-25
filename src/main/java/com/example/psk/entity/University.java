// src/main/java/com/example/psk/entity/University.java
package com.example.psk.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "university",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Student> students;

    public University() { }

    public University(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
