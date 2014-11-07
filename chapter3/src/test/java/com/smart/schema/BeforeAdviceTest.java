package com.smart.schema;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdviceTest implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
        System.out.println("------BeforeAdviceTest------");
        System.out.println("clientName:"+args[0]);
        System.out.println("------BeforeAdviceTest------");
	}
}
