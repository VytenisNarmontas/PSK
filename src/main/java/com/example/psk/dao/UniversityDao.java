package com.example.psk.dao;

import com.example.psk.entity.University;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * DAO for University (uses JPA).
 * @ApplicationScoped makes this a singleton bean in CDI.
 */
@ApplicationScoped
public class UniversityDao {

    // injects the JPA EntityManager tied to persistence-unit "pskPU"
    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    /** Find by primary key */
    public University findById(Integer id) {
        return em.find(University.class, id);
    }

    /** List all universities */
    public List<University> findAll() {
        return em.createQuery("SELECT u FROM University u", University.class)
                .getResultList();
    }

    /** Persist a new University; @Transactional gives us declarative transactions */
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
