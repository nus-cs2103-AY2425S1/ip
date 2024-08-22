public class EmptyDescriptionException extends BobbyException {
    public EmptyDescriptionException(String task) {
        super("Please fill in the description in this format: " + task + " [desc]");
    }
}
