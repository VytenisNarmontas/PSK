// src/main/java/com/example/psk/service/StudentBean.java
package com.example.psk.service;

import com.example.psk.dao.StudentDao;
import com.example.psk.dao.UniversityDao;
import com.example.psk.dao.CourseDao;
import com.example.psk.entity.Student;
import com.example.psk.entity.University;
import com.example.psk.entity.Course;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("studentBean")
@RequestScoped
public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentDao studentDao;

    @Inject
    private UniversityDao universityDao;

    @Inject
    private CourseDao courseDao;

    private Student newStudent = new Student();
    private Integer selectedUniversityId;
    private List<Integer> selectedCourseIds = new ArrayList<>();

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

    public List<Integer> getSelectedCourseIds() {
        return selectedCourseIds;
    }

    public void setSelectedCourseIds(List<Integer> selectedCourseIds) {
        this.selectedCourseIds = selectedCourseIds;
    }

    public List<University> getUniversities() {
        return universityDao.findAll();
    }

    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    public String createStudent() {
        University u = universityDao.findById(selectedUniversityId);
        newStudent.setUniversity(u);

        Set<Course> courses = selectedCourseIds.stream()
                .map(courseDao::findById)
                .collect(Collectors.toSet());
        newStudent.setCourses(courses);

        studentDao.create(newStudent);

        newStudent = new Student();
        selectedUniversityId = null;
        selectedCourseIds.clear();
        return null;
    }
}
