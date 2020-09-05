package com.zzh.kuangshenshuo.List_Security;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class set {
    public static void main(String[] args) {
        //HashSet<String> set = new HashSet<>();
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(1, 5));
                System.out.println(set);
            }, "A").start();
        }
    }
}
