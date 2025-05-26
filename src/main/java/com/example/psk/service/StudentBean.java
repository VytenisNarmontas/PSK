// src/main/java/com/example/psk/service/StudentBean.java
package com.example.psk.service;

import com.example.psk.dao.StudentDao;
import com.example.psk.dao.UniversityDao;
import com.example.psk.dao.CourseDao;
import com.example.psk.entity.Student;
import com.example.psk.entity.University;
import com.example.psk.entity.Course;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.OptimisticLockException;
import javax.ejb.EJBException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Named("studentBean")
@SessionScoped
public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private StudentDao studentDao;

    @Inject
    private UniversityDao universityDao;

    @Inject
    private CourseDao courseDao;

    @Inject
    private OptimisticLockDemo optimisticLockDemo;

    @Inject
    private AsyncWorker asyncWorker;

    // for “Add Student”
    private Student newStudent = new Student();
    private Integer selectedUniversityId;
    private List<Integer> selectedCourseIds = new ArrayList<>();

    // for “Optimistic Locking Demo”
    private Integer demoStudentId;

    // for “Asynchronous Demo”
    private Future<String> asyncResult;

    // ======== Getters & Setters ========

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

    public Integer getDemoStudentId() {
        return demoStudentId;
    }

    public void setDemoStudentId(Integer demoStudentId) {
        this.demoStudentId = demoStudentId;
    }

    public Future<String> getAsyncResult() {
        return asyncResult;
    }

    // ======== Data for selects & table ========

    public List<University> getUniversities() {
        return universityDao.findAll();
    }

    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    // ======== Actions ========

    public String createStudent() {
        University u = universityDao.findById(selectedUniversityId);
        newStudent.setUniversity(u);

        Set<Course> courses = selectedCourseIds.stream()
                .map(courseDao::findById)
                .collect(Collectors.toSet());
        newStudent.setCourses(courses);

        studentDao.create(newStudent);

        // reset form
        newStudent = new Student();
        selectedUniversityId = null;
        selectedCourseIds.clear();

        return null;
    }

    public void runOptimisticDemo() {
        try {
            optimisticLockDemo.runDemo(demoStudentId);
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Demo completed successfully",
                                    null));
        } catch (EJBException ejbEx) {
            Throwable cause = ejbEx.getCause();
            if (cause instanceof OptimisticLockException) {
                FacesContext.getCurrentInstance()
                        .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Optimistic lock failed: " + cause.getMessage(),
                                        null));
            } else {
                throw ejbEx;
            }
        }
    }

    public void startAsync() {
        asyncResult = asyncWorker.doLongComputation();
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Long task started…",
                                null));
    }

    public void checkAsync() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (asyncResult == null) {
            fc.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No task is running. Click “Start Long Task” first.",
                            null));
            return;
        }

        if (!asyncResult.isDone()) {
            fc.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Still running… please check again in a moment.",
                            null));
            return;
        }

        try {
            String result = asyncResult.get();
            fc.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Async result: " + result,
                            null));
        } catch (InterruptedException | ExecutionException e) {
            fc.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error in async task: " + e.getMessage(),
                            null));
        } finally {
            asyncResult = null;
        }
    }
}
