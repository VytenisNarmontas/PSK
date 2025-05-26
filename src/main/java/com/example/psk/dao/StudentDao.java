package com.example.psk.dao;

import com.example.psk.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StudentDao {

    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        return em.createQuery(
                "SELECT DISTINCT s FROM Student s " +
                        "LEFT JOIN FETCH s.university " +
                        "LEFT JOIN FETCH s.courses",
                Student.class
        ).getResultList();
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
