// src/main/java/com/example/psk/dao/CourseDao.java
package com.example.psk.dao;

import com.example.psk.entity.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CourseDao {

    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    /** Find by primary key */
    public Course findById(Integer id) {
        return em.find(Course.class, id);
    }

    /** List all courses */
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    /** Persist a new Course */
    @Transactional
    public void create(Course c) {
        em.persist(c);
    }
}
