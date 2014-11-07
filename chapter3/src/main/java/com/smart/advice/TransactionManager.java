package com.smart.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;


public class TransactionManager implements ThrowsAdvice, AfterReturningAdvice {
    public void afterReturning(Object returnObj, Method method, Object[] args,
                               Object obj) throws Throwable {
        returnObj = false;
        System.out.println("Please enjoy yourself!");
    }

    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception ex) throws Throwable {
        ex = null;
        System.out.println("-----------");
        System.out.println("method:" + method.getName());
        //System.out.println("抛出异常:" + ex.getMessage());
        System.out.println("成功回滚事务。");
    }
}
