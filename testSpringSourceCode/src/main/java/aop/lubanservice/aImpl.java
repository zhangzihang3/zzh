package aop.lubanservice;

import aop.a;
import org.springframework.stereotype.Repository;

@Repository("aImpl")
public class aImpl implements a {
    @Override
    public void say() {
        System.out.println("aImpl");
    }
}
