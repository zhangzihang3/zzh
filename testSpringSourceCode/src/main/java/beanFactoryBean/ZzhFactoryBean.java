package beanFactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ZzhFactoryBean implements FactoryBean {
    private String msg = "msg1,msg2,msg3";
    public void say() {
        System.out.println(" ZzhFactoryBean say");
    }

    @Override
    public Object getObject() throws Exception {
        ZzhConfigBean zzhConfigBean = new ZzhConfigBean();
        String[] m = msg.split(",");

        zzhConfigBean.setMsg1(m[0]);
        zzhConfigBean.setMsg2(m[1]);
        zzhConfigBean.setMsg3(m[2]);
        System.out.println(zzhConfigBean);
        return zzhConfigBean;
    }

    /**
     * @param
     * @method 返回值随意
     */
    @Override
    public Class<?> getObjectType() {
        return OtherBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
