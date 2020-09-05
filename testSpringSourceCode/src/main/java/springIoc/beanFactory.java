package springIoc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class beanFactory {
    //<bean id="service"  class="xml.Service"></bean>
//
//<bean id="a" class="xml.a"></bean>
//
//<bean id="b" class="xml.b">
//    <property name="a" ref="a"></property>
//</bean>
    private ConcurrentHashMap map = new ConcurrentHashMap();

    public void parseXml() throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        SAXReader reader = new SAXReader();
        Document xml = reader.read(this.getClass().getResource("/").getPath() + "//applicationfile.xml");
        //这个root相当于<beans>
        Element rootElement = xml.getRootElement();
        Object twoObject = null;

        for (Iterator<Element> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
            //twoElement相当于<bean>
            Element twoElement = iterator.next();
            String twoId = twoElement.attribute("id").getValue();
            Class<?> twoClass = Class.forName(twoElement.attribute("class").getValue());
            Iterator<Element> iterator2 = null;
            int countConstructor = 0;
            int countProperty = 0;
            //判断是否有构造函数
            if (twoElement.elementIterator().hasNext()) {
                for (iterator2 = twoElement.elementIterator(); iterator2.hasNext(); ) {
                    Element threeElement = iterator2.next();
                    if (threeElement.getName().equals("property")) {
                        countProperty++;
                    } else {
                        countConstructor++;
                    }
                }
                if(countConstructor==0){
                    twoObject = twoClass.newInstance();
                }
                //遍历<bean></bean>中的标签
                for (iterator2 = twoElement.elementIterator(); iterator2.hasNext(); ) {
                    //threeElement相当于bean中的rel、constuor
                    Element threeElement = iterator2.next();
                    if(map.get(twoId)!=null){
                        twoObject=map.get(twoId);
                    }
                    //setter方法注入
                    if (threeElement.getName().equals("property")) {
                        String threeElementName = threeElement.attribute("name").getValue();
                        String threeElementRel = threeElement.attribute("ref").getValue();
                        //获取引用的bean
                        Object relObject = map.get(threeElementRel);
                        Field declaredField = twoClass.getDeclaredField(threeElementName);
                        declaredField.setAccessible(true);
                        //为当前的<bean/>注入<ref>的bean
                        declaredField.set(twoObject, relObject);
                    } else {
                        String threeElementRel = threeElement.attribute("ref").getValue();
                        //获取ref的bean
                        Object relObject = map.get(threeElementRel);
                        twoObject = twoClass.getConstructor(relObject.getClass()).newInstance(relObject);
                    }
                }
            } else {
                //<bean/>下面没有子标签时
                twoObject = twoClass.newInstance();
            }

            map.put(twoId, twoObject);
        }
    }

    public Object getBean(String id) {
        return map.get(id);
    }
}
