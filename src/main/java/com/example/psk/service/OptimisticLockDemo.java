package com.example.psk.service;

import com.example.psk.entity.Student;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.OptimisticLockException;

@Stateless
public class OptimisticLockDemo {

    @EJB
    private OptimisticLockWorker worker;

    public void runDemo(Integer studentId) {
        try {
            // 1) load a detached copy
            Student stale = worker.loadDetached(studentId);

            // 2) bump version in the DB
            worker.updateBumpVersion(studentId);

            // 3) now try to merge the stale entity
            worker.mergeDetached(stale);

        } catch (OptimisticLockException ole) {
            System.out.println("Caught OptimisticLockException: " + ole.getMessage());
            return;
        }

        System.out.println("No OptimisticLockException was thrown!");
    }
}
