package aop.lubanservice;

import aop.a;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class prototype implements a {
    @Override
    public void say() {
        System.out.println("prototype");
    }
}
