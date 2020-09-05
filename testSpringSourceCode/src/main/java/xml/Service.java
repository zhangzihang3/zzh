package xml;

public class Service {
    //如果是default-autowire="byName"，
    //会根据setA方法中的A变成小写a，去xml配置文件中去找对应的bean
    //注入到dao dao中

    //如果是default-autowire="byType"，
    //会找实现dao dao接口的bean，去xml配置文件中去找对应的bean
    //注入到dao dao中
    dao dao;
    public Service() {
    }

    public void setA(xml.dao dao) {
        this.dao = dao;
    }



    public void say(){
        dao.say();
    }
}
