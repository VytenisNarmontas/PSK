// src/main/java/com/example/psk/dao/UniversityDao.java
package com.example.psk.dao;

import com.example.psk.entity.University;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UniversityDao {

    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    /** Find by primary key */
    public University findById(Integer id) {
        return em.find(University.class, id);
    }

    /** List all universities, eagerly fetching students */
    public List<University> findAll() {
        return em.createQuery(
                "SELECT DISTINCT u FROM University u LEFT JOIN FETCH u.students",
                University.class
        ).getResultList();
    }

    /** Persist a new University */
    @Transactional
    public void create(University uni) {
        em.persist(uni);
    }

    /** Update an existing University */
    @Transactional
    public University update(University uni) {
        return em.merge(uni);
    }
}
