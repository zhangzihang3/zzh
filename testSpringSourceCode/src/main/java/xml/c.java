package xml;

public class c implements dao{
    d d;
a a;
    public c(xml.d d) {
        this.d = d;
    }

    public void setA(xml.a a) {
        this.a = a;
    }

    @Override
    public void say() {
a.say();
        System.out.println("c");
        d.say();
    }
}
