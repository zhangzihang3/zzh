package test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class process implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        GenericBeanDefinition a = (GenericBeanDefinition) beanFactory.getBeanDefinition("a");
//        //把b.class注入到，a这个bean容器中，相当于b占据了a的bean容器，虽然b没有加compent，但是可以get到b这个bean
//        a.setBeanClass(b.class);
    }
}
