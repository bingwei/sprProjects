package com.smart.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class TransactionAfterAdvice implements AfterReturningAdvice {

    public void afterReturning(Object returnObj, Method method, Object[] args,
                               Object obj) throws Throwable {
        System.out.println("Please enjoy yourself!");
    }
}
