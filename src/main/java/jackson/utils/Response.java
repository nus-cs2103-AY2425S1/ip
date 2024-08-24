package jackson.utils;

import jackson.actions.Actions;

import java.util.regex.Matcher;

/**
 * Class to handle response.
 * Stores {@code Action.ACTIONS} and {@code Matcher} objects.
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
     * @return {@code Actions.ACTIONS} enum type
     */
    public Actions.ACTIONS getAction() {
        return this.a;
    }

    /**
     * Returns matcher stored inside.
     * @return {@code Matcher} enum type
     */
    public Matcher getMatcher() {
        return this.m;
    }
}
