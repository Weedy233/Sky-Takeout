package com.sky.aspect;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * 前置通知，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()") // Note: 使用 Before 加 切入点方法名 注解自动填充方法
    public void AutoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充...");

        // 获取操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();

        // 获取操作实体
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 针对不同的操作类型进行赋值
        try {
            if (operationType == OperationType.INSERT) {
                Method setCreateUser = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setCreateTime = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                
                setCreateUser.invoke(entity, currentId);
                setCreateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
            } else if (operationType == OperationType.UPDATE) {
                Method setUpdateUser = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(
                    AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                
                setUpdateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
