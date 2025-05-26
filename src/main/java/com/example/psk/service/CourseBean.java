package com.example.psk.service;

import com.example.psk.dao.CourseDao;
import com.example.psk.entity.Course;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("courseBean")
@RequestScoped
public class CourseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CourseDao courseDao;

    private Course newCourse = new Course();

    public Course getNewCourse() {
        return newCourse;
    }
    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }

    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    public String createCourse() {
        courseDao.create(newCourse);
        newCourse = new Course();
        return null;
    }
}
