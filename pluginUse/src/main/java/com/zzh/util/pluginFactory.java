package com.zzh.util;


import com.alibaba.fastjson.JSON;
import com.zzh.entity.PluginConfig;
import com.zzh.entity.Plugins;
import org.aopalliance.aop.Advice;
import org.apache.commons.io.IOUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @author 张子行
 * @class
 */
@Component
public class pluginFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<String, PluginConfig> cachePlugins = new HashMap();
    @Value("classpath:/pluginConfig.json")
    private Resource resource;

    /**
     * @param
     * @method 模拟把插件配置信息放入缓存，可以升级为redis
     */
    @PostConstruct
    public Collection<PluginConfig> initPlugin() {
        try {
            String config = IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
            Plugins plugins = JSON.parseObject(config, Plugins.class);
            for (PluginConfig config1 : plugins.getConfigs()) {
                cachePlugins.put(config1.getId(), config1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cachePlugins.values();
    }

    /**
     * @param
     * @method 激活插件
     */
    public void activePlugin(String id) {
        if (!cachePlugins.containsKey(id)) {
            throw new RuntimeException(String.format("这个插件不存在id=%s", id));
        }
        //获取插件的配置信息
        PluginConfig pluginConfig = cachePlugins.get(id);
        //设置激活状态
        pluginConfig.setActive(true);
        //拿到所有的beanDefinition
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //为符合条件的bean加上插件
        for (String beanDefinition : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinition);
            if (bean == this) {
                continue;
            }
            if (bean == null) {
                continue;
            }
            if (!(bean instanceof Advised)) { // 判断是否属于通知对象
                continue;
            }
            if (find(bean, pluginConfig.getClassName())) {
                continue;
            }
            try {
                //根据插件的配置信息生成通知
                Advice advice = bulidAdvice(pluginConfig);
                //bean加上通知
                ((Advised) bean).addAdvice(advice);
            } catch (Exception e) {
                System.out.println("插件添加失败");
            }

        }

    }

    /**
     * @param
     * @method 判断当前Advised是否已经包含插件中的通知
     */
    public Boolean find(Object bean, String className) {
        Advised o = ((Advised) bean);
        for (Advisor advisor : o.getAdvisors()) {
            if (className.equals(advisor.getAdvice().getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param
     * @method remove插件
     */
    public void removePlugin(String id) {
        if (!cachePlugins.containsKey(id)) {
            throw new RuntimeException(String.format("这个插件不存在id=%s", id));
        }
        //获取插件的配置信息
        PluginConfig pluginConfig = cachePlugins.get(id);
        //设置激活状态
        pluginConfig.setActive(true);
        //拿到所有的beanDefinition
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //为符合条件的bean加上插件
        for (String beanDefinition : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinition);
            if (bean == this) {
                continue;
            }
            if (bean == null) {
                continue;
            }
            if (!(bean instanceof Advised)) { // 判断是否属于通知对象
                continue;
            }
            if (find(bean, pluginConfig.getClassName())) {
                try {

                    Advice advice = bulidAdvice(pluginConfig);
                    //bean加上通知
                    ((Advised) bean).removeAdvice(advice);
                } catch (Exception e) {
                    System.out.println("插件关闭失败");
                }

            }

        }

    }

    /**
     * @param
     * @method 创建通知
     */
    public Advice bulidAdvice(PluginConfig pluginConfig) {
        Advice plugin = null;
        try {
            Boolean isLoad = false;
            //获取jar包url路径
            URL targetUrl = new URL(pluginConfig.getJarRemoteUrl());
            //获取系统类加载器
            URLClassLoader jarclassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            //遍历所有jar包url比较是否已经加载过插件
            URL[] urLs = jarclassLoader.getURLs();
            for (URL url : urLs) {
                if (targetUrl.equals(url)) {
                    out.println("jar包已经加载过了");
                    isLoad = true;
                    break;
                }
            }
            if (!isLoad) {
                //加载jar包
                Method addurl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addurl.setAccessible(true);
                addurl.invoke(jarclassLoader, targetUrl);
            }

            //加载jar包中路径为pluginConfig.getClassName()的类，生成class文件
            Class<?> pluginClass = jarclassLoader.loadClass(pluginConfig.getClassName());
            plugin = (Advice) pluginClass.newInstance();

        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return plugin;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
