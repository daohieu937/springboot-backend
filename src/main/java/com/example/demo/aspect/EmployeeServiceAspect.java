package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {
	
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceAspect.class);
	
    @Before(value = "execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
    	logger.info("Before called " + joinPoint.toString());
    }
    
    @After(value = "execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void after(JoinPoint joinPoint) {
    	logger.info("After called " + joinPoint.toString());
    }
    
    @AfterReturning(value = "execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
    	logger.info("AfterReturning called " + joinPoint.toString());
    }

    @AfterThrowing(value = "execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void afterThrowing(JoinPoint joinPoint) {
    	logger.info("AfterThrowing called " + joinPoint.toString());
    }

    @Around(value = "execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    }

    @Around("@annotation(com.example.demo.aspect.TrackTime)")
    public void aroundTrackTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    }
}