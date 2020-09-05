package TEST;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class concurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        HashMap<Object, Object> hashMap = new HashMap<>();

        int NCPU = Runtime.getRuntime().availableProcessors();
//创建一个线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 1205, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5));
        final int[] jj = {1};
//模拟100个线程并发插入数据
        for (int j = 0; j < 100; j++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    concurrentHashMap.put(jj[0]++, jj[0]++);
                    Set<Map.Entry<Object, Object>> entries = concurrentHashMap.entrySet();
                    for(Map.Entry key : entries){
                        System.out.println(key.getKey());
                    }
                }
            });
        }


        pool.shutdownNow();

    }


}
