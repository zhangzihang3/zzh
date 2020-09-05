package aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MYtest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("aop");

        //a aImpl = (a)ac.getBean("aImpl");
        //aImpl aImpl = (aImpl)ac.getBean("aImpl");
//aImpl.say();
        // System.out.println(aImpl instanceof Proxy);

        //SpringProxy a1 = (SpringProxy)ac.getBean("prototype");

        a a1 = (a) ac.getBean("prototype");
        a1.say();
        System.out.println("a1hashcode:" + a1.hashCode());
        a a2 = (a) ac.getBean("prototype");
        a2.say();
        System.out.println("a2hashcode:" + a2.hashCode());

//        a a = (a)ac.getBean("declare");
//        a.say();
    }
}
