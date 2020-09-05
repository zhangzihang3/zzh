package com.zzh.zzhThreedPool.jdk.testStatic;

/**
 * @param
 * @function Java 中被 static 修饰的成员称为静态成员或类成员。
 * 它属于整个类所有，而不是某个对象所有，即被类的所有对象所共享。
 * @return
 */
public class staticClass {

    private static int i = 100;
    private int name;

    public staticClass(int name) {
        System.out.println(i--);
        this.name = name;
    }
}
