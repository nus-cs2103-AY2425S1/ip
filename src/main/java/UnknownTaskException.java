public class UnknownTaskException extends Exception {
    public UnknownTaskException() {
        super("Sorry traveller. I am not really sure I get what you mean. Please give me the type of the task and " +
                "its description");
    }
}
