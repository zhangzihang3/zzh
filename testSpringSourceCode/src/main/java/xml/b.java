package xml;

public class b implements dao{
    a a;
    d d;

    @Override
    public void say() {
        a.say();
        System.out.println("b");
        d.say();

    }

    public void setA(xml.a a) {
        this.a = a;
    }

    public void setD(xml.d d) {
        this.d = d;
    }
}
