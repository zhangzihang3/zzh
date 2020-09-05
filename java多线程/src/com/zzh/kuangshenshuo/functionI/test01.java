package com.zzh.kuangshenshuo.functionI;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class test01 {
    public static void main(String[] args) {
        /**
         * 供给型接口只有返回值
         */
        Supplier s = () -> {
            return "Supplier";
        };
        System.out.println(s.get());
        /**
         * 函数型接口，有返回值，有形参
         */
        Function<String, String> f = (param) -> {
            return param;
        };
        System.out.println(f.apply("Function"));
        /**
         * 消费型接口，没有返回值，只有传形参
         */
        Consumer<String> c = (param) -> {
            System.out.println(param);
        };
        c.accept("Consumer");
        /**
         * 断定型接口返回类型为Boolean
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
