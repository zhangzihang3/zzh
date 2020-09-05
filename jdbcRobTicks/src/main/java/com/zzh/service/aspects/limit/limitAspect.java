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
     * @method �Խӿڽ�������
     */
    @Around("limit()")
    public R before(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, ClassNotFoundException {

        //��ȡ����ע�����õĶ���
        Object target = joinPoint.getTarget();

        //��ȡ����ע�����õĶ�������
        String targetName = target.getClass().getName();
        //��ȡ����ע�����õĶ����class
        Class<?> aClass = target.getClass();
        //��ȡ����Ĳ���
        Object[] methodParam = joinPoint.getArgs();
        //��ȡ����ע�����õķ���������
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
//        //���ݲ�������ȡ��Ӧ�Ĳ�������
//        Class<?>[] argTypes = ReflectUtils.getClasses(methodParam);
//        //�õ�ע�����õķ�����
//        Method method = aClass.getDeclaredMethod(methodName, argTypes);
//        //��ȡ�÷����ϵ�Limitע��
//        Limit annotation = method.getAnnotation(Limit.class);
//        //��ȡ�÷��������õ����������
//        int i = annotation.maxLimit();
//        System.out.println("�������Ϊ��" + i);
        StringBuffer bufferKey = new StringBuffer().append(methodName).append(targetName);
        String key = String.valueOf(bufferKey);

        Method[] methods = aClass.getMethods();
        Limit annotation = null;
        //�������еķ���
        for (Method method : methods) {
            //���ݻ�ȡ���ķ������֣�ƥ���ȡ�÷���
            if (methodName.equals(method.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                //�����еĲ���ƥ�䣬��ȷƥ�䷽��
                if (parameterTypes.length == args.length) {
                    annotation = method.getAnnotation(Limit.class);
                }
            }
        }

        if (null != annotation) {
            Semaphore semaphore = semaphores.get(key);
            if (null == semaphore) {
                //semaphores.put()
                //��ʼ�������ӿڵķ�������
                //System.out.println("maxLimit:" + annotation.maxLimit());
                semaphores.putIfAbsent(String.valueOf(key), new Semaphore(annotation.maxLimit()));
                semaphore = semaphores.get(key);
            }
            try {
                //���ﵽ���ķ��ʵ�������ֻ�е��п��е�����ʱ������˲��ܼ���
                semaphore.acquire();
                //ִ�з���
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
