package com.zzh.kuangshenshuo.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 张子行
 * @class cas 比较替换技术
 */
public class test01 {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(10);
//        System.out.println(atomicInteger.compareAndSet(10, 20));
//        atomicInteger.getAndIncrement();
//        System.out.println(atomicInteger.compareAndSet(10, 20));

        AtomicStampedReference<Integer> cas = new AtomicStampedReference<>(1, 1);
        Integer reference = cas.getReference();
        int stamp = cas.getStamp();
        boolean b = cas.compareAndSet(reference, cas.getReference() + 10, stamp, cas.getStamp() + 1);
        System.out.println(b);
        System.out.println(cas.getReference());
    }
}
