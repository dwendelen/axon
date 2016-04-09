package axon.core.hello;

public class HelloCommand {
    private String hello;

    public HelloCommand(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }
}
