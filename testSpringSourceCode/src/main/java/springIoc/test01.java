package springIoc;

import org.dom4j.DocumentException;
import org.junit.Test;
import xml.a;
import xml.dao;

import java.lang.reflect.InvocationTargetException;

public class test01 {
    @Test
    public void test() throws ClassNotFoundException, InstantiationException, DocumentException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        beanFactory beanFactory = new beanFactory();
        beanFactory.parseXml();
        dao dao = (dao)beanFactory.getBean("c");
        dao.say();

    }

}
