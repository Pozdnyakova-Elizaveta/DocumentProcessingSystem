package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Аспект для логирования
 */
@Component
@Aspect
public class LoggingAspect {
    /**
     * Объект для записи логов
     */
    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    /**
     * Точка среза
     */
    @Pointcut("within(org.example.controller.DocumentController)")
    public void pointcut() {
    }

    /**
     * Вывод лога перед выполнением метода
     *
     * @param joinPoint точка соединения для получения информации о методе
     */
    @After("pointcut()")
    public void logInfoMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.log(Level.INFO, "Название метода " + methodName + ", аргументы метода " + List.of(args));
    }
}
