package com.zzh.service.aspects.log;

import com.zzh.bean.Person;
import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@SuppressWarnings("all")
@Aspect
@Component
public class logAspect {
    @Pointcut("@annotation(com.zzh.service.aspects.log.SysLog)")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) {
        /**
         * TODO 这里可以收集用户的一些ip，name等信息
         */

        ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("请求方法：" + name);
        String description = saveLog(joinPoint);
    }

    /**
     * @param MethodSignature signature = (MethodSignature)joinPoint.getSignature();
     *                        Method method = signature.getMethod();
     *                        SysLog annotation = method.getAnnotation(SysLog.class);
     *                        String description1 = annotation.description();
     *                        System.out.println(description1);
     * @method 这样获取到的方法一样
     */
    private String saveLog(JoinPoint joinPoint) {
        String description = null;
        //获取作用切面的目标类的class
        Class<?> aClass = joinPoint.getTarget().getClass();
        //获取作用方法的名字
        String methodName = joinPoint.getSignature().getName();
        //根据class获取所有该class下的方法
        Method[] methods = aClass.getMethods();
        //获取作用方法上的参数
        Object[] args = joinPoint.getArgs();
        Method tagerMethod = null;
        //遍历所有的方法
        for (Method method : methods) {
            //根据获取到的方法名字，匹配获取该方法
            if (methodName.equals(method.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                //方法中的参数匹配，精确匹配方法
                if (parameterTypes.length == args.length) {
                    tagerMethod = method;
                    description = method.getAnnotation(SysLog.class).description();
                }
            }
        }
        return description;
    }


}
