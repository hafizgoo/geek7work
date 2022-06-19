package com.hafizgoo.masterslave.aop;

/**
 * @Auther: hafizgoo
 * @Date: DATE−2022/6/19 - MONTH−06 - DAY−19 - TIME−22:39
 * @Description: com.hafizgoo.masterslave.aop
 * @version: 1.0
 */


import com.hafizgoo.masterslave.config.AnnoDataSource;
import com.hafizgoo.masterslave.config.DataSourceNames;
import com.hafizgoo.masterslave.config.MultiDatasource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect{

    @Autowired
    private MultiDatasource multiDatasource;

    @Pointcut("@annotation(com.github.zibuyu28.homework112801.config.AnnoDataSource)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AnnoDataSource annotation = method.getAnnotation(AnnoDataSource.class);
        if(annotation == null || "".equals(annotation.value())) {
            multiDatasource.setDataSource(DataSourceNames.MASTER);
        } else {
            multiDatasource.setDataSource(annotation.value());
        }

        try {
            return point.proceed();
        } finally {
            multiDatasource.removeDataSource();
        }
    }
}
