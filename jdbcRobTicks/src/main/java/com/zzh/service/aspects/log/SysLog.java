package com.zzh.service.aspects.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD) //表明这个注解是作用在方法上的
public @interface SysLog {
    String description() default "";
}