public class Message {
    private String description;

    /**
     * Constructor for a new Message.
     *
     * @param description The description of the message.
     */
    public Message(String description) {
        this.description = description;
    }

    private String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }

    /**
     * Prints out the message. Every instance of Message has the same print format.
     */
    public final void print() {
        String wrapped = wrapStringWithHorizontalLines(description);
        System.out.println(wrapped);
    }
}
