package com.zzh.kuangshenshuo.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class demo {

    static AtomicStampedReference<Integer> money =new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {

            int stamp = money.getStamp();

            System.out.println("stamp��ֵ��"+stamp);

            new Thread(){         //��ֵ�߳�

                @Override
                public void run() {

                    while (true){

                        Integer account = money.getReference();

                        if (account<20){

                            if (money.compareAndSet(account,account+20,stamp,stamp+1)){

                                System.out.println(Thread.currentThread().getName()+"���С��20Ԫ����ֵ�ɹ���Ŀǰ��"+money.getReference()+"Ԫ");
                                break;
                            }
                        }else {
                            System.out.println(Thread.currentThread().getName()+"������20Ԫ,�����ֵ");
                        }
                    }
                }
            }.start();
        }


        new Thread(){

            @Override
            public void run() {    //�����߳�

                for (int j = 0; j < 100; j++) {

                    while (true){

                        int timeStamp = money.getStamp();//1

                        int currentMoney =money.getReference();//39

                        if (currentMoney>10){
                            System.out.println("��ǰ�˻�������10Ԫ");
                            if (money.compareAndSet(currentMoney,currentMoney-10,timeStamp,timeStamp+1)){

                                System.out.println(Thread.currentThread().getName()+"�����߳ɹ�����10Ԫ�����"+money.getReference());

                                break;
                            }
                        }else {
                            System.out.println(Thread.currentThread().getName()+"û���㹻�Ľ��");

                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        }catch (Exception ex){
                            ex.printStackTrace();
                            break;
                        }

                    }

                }
            }
        }.start();

    }
}

