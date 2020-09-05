package beanFactoryBean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(config.class);
        // 拿到真正的工厂bean得加&
         //ZzhFactoryBean zzhFactoryBean = (ZzhFactoryBean)context.getBean("&zzhFactoryBean");

        //如果直接拿，拿到的是ZzhFactoryBean中的getObject返回的bean
        ZzhConfigBean zzhFactoryBean = (ZzhConfigBean)context.getBean("zzhFactoryBean");
        zzhFactoryBean.say();

    }
}
