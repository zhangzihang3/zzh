package test;

import org.junit.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.net.URLClassLoader;

//自定义beanname生成规则
//单例bean调用原型bean，保证
public class start {
    public static void main(String[] args) {
        //AutowireCapableBeanFactory

        ClassPathXmlApplicationContext ca = new ClassPathXmlApplicationContext("applicationfile.xml");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(config.class);
        Object a = ac.getBean("a");
        System.out.println(a);
        System.out.println(ac.getBean(a.class));
    }
    @Test
    public void test() throws NoSuchMethodException {

        URLClassLoader classLoader = (URLClassLoader)getClass().getClassLoader();

        System.out.println(classLoader.getURLs().toString());
        System.out.println(URLClassLoader.class.getDeclaredMethod("addURL", URL.class));
    }
}
