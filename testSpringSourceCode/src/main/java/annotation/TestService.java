package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
//Resource与Autowired的区别

//1：如果是@Resource(name = "a")，去找名字为a的bean
//会根据setA方法中的A变成小写a，去xml配置文件中去找对应的bean
//相当于"byName"
//如果是@Autowired，有多个实现类的条件下
//会找实现dao a接口的bean，发现有多个实现类，接着找名字为a的bean
//相当于bytype 在 byname
//2：@Resource遵循java规范扩展性更强，换一个容器也可以执行

//如果是@Qualifier，有多个实现类的条件下,相当于byname，指定查找
@Service
public class TestService {


//   @Resource(name = "b")
//    @Qualifier(value = "a")
    @Autowired
   dao a;
    public TestService() {
    }





    public void say(){
        a.say();
    }
}
