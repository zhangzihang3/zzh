package com.zzh.zzhThreedPool.jdk.testStatic;

/**
 * @param
 * @function Java �б� static ���εĳ�Ա��Ϊ��̬��Ա�����Ա��
 * ���������������У�������ĳ���������У�����������ж���������
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
