package proxy;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class test01 {
    public static void main(String[] args) {
        c target = new c();
        System.out.println(c.class.getInterfaces()[0].getName());
        System.out.println(c.class.getInterfaces()[0].getSimpleName());
        proxyc proxyc = new proxyc(target);
        proxyc.say();
        proxyc.notify();
    }
    @Test
    public void proxy(){
        c target = new c();
        System.out.println("target.hashCode: "+target.getClass());
        a proxy = (a)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                System.out.println("jdk proxy");
                System.out.println(method.getName());
                System.out.println("proxy.hashCode: "+proxy.getClass());
                return method.invoke(target);
            }
        });
        proxy.say();

    }
}
