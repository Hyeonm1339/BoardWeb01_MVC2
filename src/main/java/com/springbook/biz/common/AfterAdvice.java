package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void afterAdvicePoint() {}
	
//	@After("afterAdvicePoint()")
	public void finallyLog() {
		System.out.println("[AfterAdvice] 비즈니스 로직 수행 후 동작");
	}
}
