package com.zzh.service.aspects.limit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.METHOD)
public @interface Limit {
    //默认限制流量
    int maxLimit() default 10;

}
