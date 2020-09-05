package aop;

import aop.lubanservice.aImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect("perthis(this(aop.a))")
@Component
@Scope("prototype")
public class MyAspect {

//    @Before("this(aop.a)")
//    public void b(){
//        System.out.println("before");
//    }
//
//    @Before("target(aop.lubanservice.aImpl)")
//    public void c(){
//        System.out.println("before");
//    }

//    @Before("this(aop.lubanservice.aImpl)")
//    public void b(){
//        System.out.println("before");
//    }

//    @Before("execution(public void aop.lubanservice.aImpl.say())")
//    public void a(){
//        System.out.println("before");
//    }
    @Before("execution(* aop.lubanservice.*.*(..))")
    public void a1(){
        System.out.println(this.hashCode());
        System.out.println("before");
    }
     @DeclareParents(value = "aop.lubanservice.*",defaultImpl = aImpl.class)
    a a;
}
