package com.lg.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * Create by MIO on 2017/11/11 20:23
 */
@Component
@Aspect
public class AuditBean {
    @Around("within(com.lg.cloud_note.service..*)")
    public Object audit(
            ProceedingJoinPoint point){
        Object obj = null;

        try {
            long timeStart = System.currentTimeMillis();
            obj = point.proceed();
            long timeEnd = System.currentTimeMillis();
            String str = point.getSignature().toString();
            System.out.println(str + "耗时：" + (timeEnd - timeStart));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return obj;
    }
}
