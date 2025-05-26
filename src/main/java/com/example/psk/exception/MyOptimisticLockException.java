package com.example.psk.exception;

import javax.ejb.ApplicationException;
import javax.persistence.OptimisticLockException;

@ApplicationException(rollback = true)
public class MyOptimisticLockException extends OptimisticLockException {
    public MyOptimisticLockException(String message) {
        super(message);
    }

    // added so you can wrap the original cause
    public MyOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
