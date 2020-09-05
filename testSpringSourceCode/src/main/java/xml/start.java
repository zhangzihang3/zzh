package xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class start {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ca = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");
        Service dao = (Service)ca.getBean("service");
        dao.say();
    }
}
