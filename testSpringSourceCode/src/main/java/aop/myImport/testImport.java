package aop.myImport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {importBean.class})
@Configuration
public class testImport {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testImport.class);
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("====="+name);
        }
        importBean importBean = (importBean)ac.getBean(importBean.class);
        importBean.say();
    }

}
