package com.example.psk.dao;

import com.example.psk.entity.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * DAO for Student (uses JPA).
 */
@ApplicationScoped
public class StudentDao {

    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Transactional
    public void create(Student s) {
        em.persist(s);
    }

    @Transactional
    public Student update(Student s) {
        return em.merge(s);
    }

    @Transactional
    public void delete(Student s) {
        Student managed = em.contains(s) ? s : em.merge(s);
        em.remove(managed);
    }
}
