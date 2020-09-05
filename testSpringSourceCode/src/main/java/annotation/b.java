package annotation;

import annotation.dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class b implements dao {
    @Override
    public void say() {
        System.out.println("b");
    }
}
