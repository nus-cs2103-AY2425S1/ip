public class EmptyDescriptionException extends Exception {
    EmptyDescriptionException() {
        super("The description cannot be empty!");
    }
}
