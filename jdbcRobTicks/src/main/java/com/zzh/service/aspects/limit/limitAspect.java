package com.zzh.service.aspects.limit;

import com.zzh.service.aspects.log.SysLog;
import com.zzh.utils.R;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Aspect
@Component
public class limitAspect {
    private ConcurrentHashMap<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.zzh.service.aspects.limit.Limit)")
    public void limit() {

    }

    /**
     * @param
     * @method 对接口进行限流
     */
    @Around("limit()")
    public R before(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, ClassNotFoundException {

        //获取被该注解作用的对象
        Object target = joinPoint.getTarget();

        //获取被该注解作用的对象名字
        String targetName = target.getClass().getName();
        //获取被该注解作用的对象的class
        Class<?> aClass = target.getClass();
        //获取请求的参数
        Object[] methodParam = joinPoint.getArgs();
        //获取被该注解作用的方法的名字
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
//        //根据参数，获取对应的参数类型
//        Class<?>[] argTypes = ReflectUtils.getClasses(methodParam);
//        //得到注解作用的方法了
//        Method method = aClass.getDeclaredMethod(methodName, argTypes);
//        //获取该方法上的Limit注解
//        Limit annotation = method.getAnnotation(Limit.class);
//        //获取该方法上设置的最大限流量
//        int i = annotation.maxLimit();
//        System.out.println("最大流量为：" + i);
        StringBuffer bufferKey = new StringBuffer().append(methodName).append(targetName);
        String key = String.valueOf(bufferKey);

        Method[] methods = aClass.getMethods();
        Limit annotation = null;
        //遍历所有的方法
        for (Method method : methods) {
            //根据获取到的方法名字，匹配获取该方法
            if (methodName.equals(method.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                //方法中的参数匹配，精确匹配方法
                if (parameterTypes.length == args.length) {
                    annotation = method.getAnnotation(Limit.class);
                }
            }
        }

        if (null != annotation) {
            Semaphore semaphore = semaphores.get(key);
            if (null == semaphore) {
                //semaphores.put()
                //初始化各个接口的访问流量
                //System.out.println("maxLimit:" + annotation.maxLimit());
                semaphores.putIfAbsent(String.valueOf(key), new Semaphore(annotation.maxLimit()));
                semaphore = semaphores.get(key);
            }
            try {
                //当达到最大的访问的流量后，只有等有空闲的流量时，别的人才能加入
                semaphore.acquire();
                //执行方法
                joinPoint.proceed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
        return R.ok();
    }

}
