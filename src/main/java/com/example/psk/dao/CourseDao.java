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

    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    @Transactional
    public void create(Course c) {
        em.persist(c);
    }
}
