package cumplido.miguel.heroes.heroes.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ManualTimerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ManualTimerAspect.class);


    @Around("@annotation(ManualTimer)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object invokedMethod = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if (logger.isInfoEnabled()){
            logger.info(
                    "Method {} executed in {} ms", joinPoint.getSignature().toShortString(), executionTime);
        }

        return invokedMethod;
    }
}
