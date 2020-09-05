package proxy;

public class proxyc implements a{
    private c c;
    public proxyc(c c){
        this.c = c;
    }
    @Override
    public String say() {
        System.out.println("im proxyc");
        c.say();
        return "";
    }


}
