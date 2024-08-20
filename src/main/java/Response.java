import java.util.regex.Matcher;

/**
 * Class to handle response. Store Action.ACTIONS and Matcher.
 */
public class Response {
    private Actions.ACTIONS a;
    private Matcher m;

    public Response(Actions.ACTIONS a, Matcher m) {
        this.a = a;
        this.m = m;
    }

    /**
     * Returns action stored inside.
     * @return Actions.ACTIONS enum type
     */
    public Actions.ACTIONS getAction() {
        return this.a;
    }

    /**
     * Returns matcher stored inside.
     * @return Matcher enum type
     */
    public Matcher getMatcher() {
        return this.m;
    }
}
