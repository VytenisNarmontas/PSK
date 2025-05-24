package com.example.psk.service;

import com.example.psk.dao.StudentDao;
import com.example.psk.dao.UniversityDao;
import com.example.psk.entity.Student;
import com.example.psk.entity.University;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * A CDI bean, request-scoped, backing UI actions on students.
 * - @RequestScoped: one instance per HTTP request.
 * - @Inject: injects DAO beans.
 */
@RequestScoped
public class StudentBean {

    @Inject
    private StudentDao studentDao;

    @Inject
    private UniversityDao universityDao;

    // model for the “new student” form
    private Student newStudent = new Student();

    // selected university for binding
    private Integer selectedUniversityId;

    public Student getNewStudent() {
        return newStudent;
    }

    public void setSelectedUniversityId(Integer selectedUniversityId) {
        this.selectedUniversityId = selectedUniversityId;
    }

    public Integer getSelectedUniversityId() {
        return selectedUniversityId;
    }

    /** List all unis for a dropdown */
    public List<University> getUniversities() {
        return universityDao.findAll();
    }

    /** List all students for display */
    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    /**
     * Called by the UI form to create a new student.
     * CDI + @Transactional on the DAO takes care of begin/commit.
     */
    public String createStudent() {
        University uni = universityDao.findById(selectedUniversityId);
        newStudent.setUniversity(uni);
        studentDao.create(newStudent);
        // reset form
        newStudent = new Student();
        selectedUniversityId = null;
        // stay on same page
        return null;
    }
}
