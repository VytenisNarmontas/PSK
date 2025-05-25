// src/main/java/com/example/psk/service/StudentBean.java
package com.example.psk.service;

import com.example.psk.dao.StudentDao;
import com.example.psk.dao.UniversityDao;
import com.example.psk.entity.Student;
import com.example.psk.entity.University;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("studentBean")
@RequestScoped
public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentDao studentDao;

    @Inject
    private UniversityDao universityDao;

    // backing model for the “Add Student” form
    private Student newStudent = new Student();

    // will hold the selected university’s ID
    private Integer selectedUniversityId;

    // --- getters & setters ---

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public Integer getSelectedUniversityId() {
        return selectedUniversityId;
    }

    public void setSelectedUniversityId(Integer selectedUniversityId) {
        this.selectedUniversityId = selectedUniversityId;
    }

    // expose data for dropdowns and table
    public List<University> getUniversities() {
        return universityDao.findAll();
    }

    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    /** Called by the JSF form to persist a new student */
    public String createStudent() {
        // look up the University by its ID
        University uni = universityDao.findById(selectedUniversityId);
        newStudent.setUniversity(uni);

        studentDao.create(newStudent);

        // reset form for the next entry
        newStudent = new Student();
        selectedUniversityId = null;

        // stay on same page
        return null;
    }
}
