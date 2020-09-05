package com.zzh.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class pluginAspect {
   @Before(value = ("execution(* com.zzh.controller.*.*(..))"))
    public void oo(){}
}
