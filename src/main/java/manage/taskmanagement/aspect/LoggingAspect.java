package manage.taskmanagement.aspect;

import java.util.logging.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static Logger logger = Logger.getLogger("manage.taskmanagement.aspect");

    @Pointcut("execution(* manage.taskmanagement.*.*.*(..)) " )


    public void loggingPointcut() {}

    @Around("loggingPointcut()")
    public Object loggingAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();

        logger.log(Level.INFO, "Inside " + className + " class, " + methodName + " method.");

        return pjp.proceed();
    }

    @Before("execution(* manage.taskmanagement.controller.*.get*(..))")
    public void logStatementBefore() {
        logger.log(Level.INFO, "Executing a getter!");
    }

    @After("execution(* manage.taskmanagement.service.TaskHistoryService.*(..))")
    public void logStatementAfter() {
        logger.log(Level.INFO, "Execution completed.");
    }
}