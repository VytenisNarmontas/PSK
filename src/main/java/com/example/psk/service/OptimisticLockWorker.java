package com.example.psk.service;

import com.example.psk.entity.Student;
import com.example.psk.exception.MyOptimisticLockException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

@Stateless
public class OptimisticLockWorker {

    @PersistenceContext(unitName = "pskPU")
    private EntityManager em;

    /** Runs in its own transaction, finds and detaches the student. */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Student loadDetached(Integer id) {
        Student s = em.find(Student.class, id);
        em.detach(s);
        return s;
    }

    /** Runs in its own transaction, bumps the version by updating the row. */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateBumpVersion(Integer id) {
        Student s = em.find(Student.class, id);
        s.setStudentName(s.getStudentName() + " [T1]");
        // when this tx commits, version++ happens
    }

    /**
     * Runs in its own transaction, attempts to merge the old detached entity.
     * Wraps any JPA OptimisticLockException in your MyOptimisticLockException.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void mergeDetached(Student detached) {
        try {
            em.merge(detached);
        } catch (OptimisticLockException ole) {
            throw new MyOptimisticLockException(
                    "Optimistic lock failed for Student ID=" + detached.getId(),
                    ole
            );
        }
    }
}
