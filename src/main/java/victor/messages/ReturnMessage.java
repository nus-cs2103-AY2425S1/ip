package victor.messages;

/**
 * A ReturnMessage class that contains an array of the strings returned from the Command executions.
 */
public class ReturnMessage {
    private String[] message;
    private boolean isEmpty;

    /**
     * Constructor for the ReturnMessage class, sets the isEmpty boolean to
     * true if no messages were input. Using Varargs here.
     * @param messages A variable number of String arguments for the return message
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

    /**
     * Gets all the return messages from commands as one string.
     * @return A string with all return messages separated by newlines.
     */
    public String getMessagesAsString() {
        if (this.isEmpty()) {
            return "  ~  Sorry, something went wrong! Please try again!";
        }
        String returnString = "";
        for (String string : getMessages()) {
            returnString += string + "\n";
        }
        return returnString.trim();
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }
}
