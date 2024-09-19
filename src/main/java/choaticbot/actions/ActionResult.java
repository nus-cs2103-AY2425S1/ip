package choaticbot.actions;

/**
 * Represents the result of an action, encapsulating a message.
 */
public class ActionResult {
    private String msg;

    /**
     * Constructs an {@code ActionResult} with the specified message.
     *
     * @param msg the message describing the result of an action
     */
    public ActionResult(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the message associated with this action result.
     *
     * @return the message as a {@code String}
     */
    public String getMessage() {
        return this.msg;
    }
}
