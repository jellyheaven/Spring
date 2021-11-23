package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("[메소드 호출전 : LogginAdvice]");
		System.out.println(invocation.getMethod() + "메소드 호출전");
		
		Object obj = invocation.proceed();
		
		System.out.println("[메서드 호출 후 : loggingAdvie]");
		System.out.println(invocation.getMethod() +"메서드 호출 후 ");
		
		return obj;
	}
	
}
