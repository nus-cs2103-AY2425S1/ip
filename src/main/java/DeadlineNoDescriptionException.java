public class DeadlineNoDescriptionException extends TaskException {
    public DeadlineNoDescriptionException() {
        super("The description of a description cannot be empty!");
    }
}