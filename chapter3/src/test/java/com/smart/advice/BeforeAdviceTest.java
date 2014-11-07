package com.smart.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.*;

public class BeforeAdviceTest {
    private Waiter target;
    private BeforeAdvice advice;
    private ProxyFactory pf;

    @BeforeTest
    public void init() {
        target = new NaiveWaiter();
        advice = new GreetingBeforeAdvice();
        //①Spring提供的代理工厂
        pf = new ProxyFactory();
        // ②设置代理目标
        pf.setTarget(target);
        //③为代理目标添加增强
        pf.addAdvice(advice);
    }

    @Test
    public void beforeAdvice() {
        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }
}
