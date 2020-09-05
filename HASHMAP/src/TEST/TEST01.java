package TEST;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TEST01 {
    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("1", 1);
        Integer oleValue = hashMap.put("1", 2);
        System.out.println(oleValue);
        System.out.println(2<<1);
        int a=1,b=2,c=3,d=4;
        if((a=b)==2||(c=d)==2){
            System.out.println("uuuuuuuuuuuuuuuuu"+a+c);
        }
        if(true){
            System.out.println("+++");
        }else if(true){
            System.out.println("---");
        }else{
            System.out.println("111111");
        }
    }
}
