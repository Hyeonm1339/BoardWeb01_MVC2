package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	
//	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointCut() {}
	
//	@Around("allPointCut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("[BEFORE]:비즈니스메소드 수행 전 내용");
//		Object returnObj = pjp.proceed();
//		System.out.println("[AFTER] 비즈니스 메소드 수행 후");

		String method = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		Object returnObj = pjp.proceed();
		stopWatch.stop();

		System.out.println("AroundAdvice실행 " + method + "() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");

		return returnObj;
	}

}
