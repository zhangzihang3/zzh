package com.zzh.kuangshenshuo.functionI;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class test01 {
    public static void main(String[] args) {
        /**
         * �����ͽӿ�ֻ�з���ֵ
         */
        Supplier s = () -> {
            return "Supplier";
        };
        System.out.println(s.get());
        /**
         * �����ͽӿڣ��з���ֵ�����β�
         */
        Function<String, String> f = (param) -> {
            return param;
        };
        System.out.println(f.apply("Function"));
        /**
         * �����ͽӿڣ�û�з���ֵ��ֻ�д��β�
         */
        Consumer<String> c = (param) -> {
            System.out.println(param);
        };
        c.accept("Consumer");
        /**
         * �϶��ͽӿڷ�������ΪBoolean
         */
        Predicate p = (t) -> {
            if (t != null) {
                return true;
            }else{
                return false;
            }
        };
        System.out.println(p.test(1));
    }
}
