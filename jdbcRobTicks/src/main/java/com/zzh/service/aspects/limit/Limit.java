package com.zzh.service.aspects.limit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.METHOD)
public @interface Limit {
    //Ĭ����������
    int maxLimit() default 10;

}
