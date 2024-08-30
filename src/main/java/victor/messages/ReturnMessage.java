package victor.messages;

/**
 * A ReturnMessage class that contains an array of the strings returned from the Command executions.
 */
public class ReturnMessage {
    private String[] message;
    private boolean isEmpty;

    /**
     * Constructor for the ReturnMessage class, sets the isEmpty boolean to
     * true if no messages were input.
     * @param messages A variable number of String argu1ments for the return message
     *     of the commands.
     */
    public ReturnMessage(String... messages) {
        this.message = messages;
        if (messages.length != 0) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
    }

    public String[] getMessages() {
        return this.message;
    }

    public boolean checkIsEmpty() {
        return this.isEmpty;
    }
}
