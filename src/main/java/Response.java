import java.util.regex.Matcher;
public class Response {
    private Actions.ACTIONS a;
    private Matcher m;

    public Response(Actions.ACTIONS a, Matcher m) {
        this.a = a;
        this.m = m;
    }

    public Actions.ACTIONS getAction() {
        return this.a;
    }

    public Matcher getMatcher() {
        return this.m;
    }
}
