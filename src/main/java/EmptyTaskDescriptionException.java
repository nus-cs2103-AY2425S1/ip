public class EmptyTaskDescriptionException extends Exception {
    public EmptyTaskDescriptionException() {
        super("My dear traveller, the task description cannot be empty. Please give me something specific.");
    }
}
