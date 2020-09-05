package com.zzh.test;

import com.zzh.config.pluginConfig;
import com.zzh.util.pluginFactory;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URLClassLoader;

public class test {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(pluginConfig.class);
        pluginFactory pluginFactory = (pluginFactory)ac.getBean("pluginFactory");
        pluginFactory.activePlugin("1");
    }
    @Test
    public void a(){
        System.out.println((URLClassLoader) getClass().getClassLoader());
    }
}
