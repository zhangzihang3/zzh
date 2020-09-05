package aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = false)
@Configuration
@ComponentScan(value = "aop")
public class config {
//    AnnotationAwareAspectJAutoProxyCreator
}
