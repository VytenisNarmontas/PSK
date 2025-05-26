package com.example.psk.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Loggable
@Interceptor
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        System.out.println("[LOG] Entering " + ctx.getMethod().getDeclaringClass().getSimpleName()
                + "." + ctx.getMethod().getName());
        try {
            Object result = ctx.proceed();
            System.out.println("[LOG] Exiting " + ctx.getMethod().getName()
                    + " with result = " + result);
            return result;
        } catch (Exception e) {
            System.out.println("[LOG] Exception in " + ctx.getMethod().getName() + ": " + e);
            throw e;
        }
    }
}
