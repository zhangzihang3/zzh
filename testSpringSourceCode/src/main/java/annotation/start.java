package annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*@ComponentScan(value = "annotation",
        excludeFilters = {@ComponentScan.Filter( //不扫描哪些组件
                pattern="annotation.impl.*", //一定要加*
                type = FilterType.REGEX)}) //指定pattern类型
*/
@ComponentScan(value = "annotation")
@Configuration
public class start {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ca =
                new AnnotationConfigApplicationContext();

        //ca.getEnvironment().setActiveProfiles("b"); 哪些bean是属于这个生产环境的就能被加载
        ca.register(start.class);
        ca.refresh();
        TestService testService = (annotation.TestService)ca.getBean("testService");
        testService.say();
    }
}
