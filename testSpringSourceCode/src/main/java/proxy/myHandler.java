package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class myHandler implements myHadlerI{
    private Object target;
    public myHandler(Object target){
        this.target= target;
    }

    public Object invoke(Method method) throws InvocationTargetException, IllegalAccessException {
        System.out.println("myCoustom proxy");
        return (String)method.invoke(target);

    }
}
