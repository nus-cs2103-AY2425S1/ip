public class EmptyDescriptionException extends Exception {
    EmptyDescriptionException() {
        super("The description of a todo cannot be empty!");
    }
}
