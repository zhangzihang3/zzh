package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class a {

    public a() {
        System.out.println("Constructor from a");
    }

    public void sayB() {
        System.out.println("aaaaaaaa");
    }
}
