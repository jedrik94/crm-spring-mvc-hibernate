package pl.jedrik94.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* pl.jedrik94.demo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* pl.jedrik94.demo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* pl.jedrik94.demo.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("DEBUG (before): method name - " + methodName);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            logger.info("DEBUG (before): method argument - " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("DEBUG (afterReturning): method name - " + methodName);

        logger.info("DEBUG (afterReturning): method result - " + result);
    }
}
