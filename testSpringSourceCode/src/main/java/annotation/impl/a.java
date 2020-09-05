package annotation.impl;

import annotation.dao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
//InitializingBean DisposableBeanbean初始化时做操作
@Component
public class a implements dao, InitializingBean , DisposableBean {
    @Override
    public void say() {
        System.out.println("a");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destoy");
    }
}
