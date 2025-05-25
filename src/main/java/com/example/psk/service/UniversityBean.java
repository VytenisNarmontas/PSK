// src/main/java/com/example/psk/service/UniversityBean.java
package com.example.psk.service;

import com.example.psk.dao.UniversityDao;
import com.example.psk.entity.University;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for university.xhtml.
 */
@Named("universityBean")
@RequestScoped
public class UniversityBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UniversityDao universityDao;

    private University newUniversity = new University();

    public List<University> getUniversities() {
        return universityDao.findAll();
    }

    public University getNewUniversity() {
        return newUniversity;
    }

    public void setNewUniversity(University newUniversity) {
        this.newUniversity = newUniversity;
    }

    /** Called when the form is submitted */
    public String create() {
        universityDao.create(newUniversity);
        newUniversity = new University();
        return null;
    }
}
