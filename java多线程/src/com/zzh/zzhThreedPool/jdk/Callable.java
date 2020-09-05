package com.zzh.zzhThreedPool.jdk;

import java.util.concurrent.*;

public class Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> f = executorService.submit(new myCallAble(1, 2));
        testFuture(f);
        boolean b = f.cancel(true);
        System.out.println("ȡ������ִ�еĽ��:"+b);
        Integer v = f.get(1, TimeUnit.SECONDS);//���ڵȴ�ʱ�����,����������ִ�����,�ᱨ�쳣
        System.out.println("����ִ�еĽ����:"+v);
    }

    public static void testFuture(Future<Integer> f) throws ExecutionException, InterruptedException {
        System.out.println("��һ���ж������Ƿ�ȡ��:"+f.isCancelled());
        System.out.println("��һ���ж������Ƿ�ر�:"+f.isDone());
        //get()һֱ�ȴ������ִ�У�ֱ������
        Integer result = f.get();
        System.out.println("����ִ�еĽ��Ϊ��"+result);
        System.out.println("�ڶ����ж������Ƿ�ȡ��:"+f.isCancelled());
        System.out.println("�ڶ����ж������Ƿ�ر�:"+f.isDone());

    }

    static class myCallAble implements java.util.concurrent.Callable<Integer> {
        private int i;
        private int j;

        public myCallAble(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public Integer call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println(name + "׼����ʼ����");
            int result = i + j;
            Thread.sleep(2000);
            System.out.println(name + "�������");
            return result;
        }
    }
}
