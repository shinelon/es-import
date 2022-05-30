package com.shinelon.esimport.aop;

import com.shinelon.esimport.EsImportApplication;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author shinelon
 */
@Aspect
@Component
@Slf4j
public class ExceptionAop {

    @Pointcut(value = "execution(public * com.shinelon.esimport..*.*(..))")
    public void serviceCut() {

    }

    @AfterThrowing(value = "serviceCut()", throwing = "exception")
    public void throwing(JoinPoint joinPoint, Throwable exception) {
        log.error(exception.getMessage(), exception);
        System.out.println("\n" + EsImportApplication.getHelpString());
        System.exit(0);
    }
}
