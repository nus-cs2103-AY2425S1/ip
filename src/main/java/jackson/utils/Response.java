package jackson.utils;

import java.util.regex.Matcher;

import jackson.actions.Actions;

/**
 * Class to handle response.
 * Stores {@code Action.ACTIONS} and {@code Matcher} objects.
 */
public class Response {
    private Actions.ActionType action;
    private Matcher matcher;

    /**
     * Constructor for Response class.
     * @param action {@code Actions.ActionType} object containing what type of action to take
     * @param matcher {@code Matcher} object containing regex matches
     */
    public Response(Actions.ActionType action, Matcher matcher) {
        this.action = action;
        this.matcher = matcher;
    }

    /**
     * Returns action stored inside.
     * @return {@code Actions.ACTIONS} enum type
     */
    public Actions.ActionType getAction() {
        return this.action;
    }

    /**
     * Returns matcher stored inside.
     * @return {@code Matcher} enum type
     */
    public Matcher getMatcher() {
        return this.matcher;
    }
}
