public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String commandType) {
        super("OOPS!!! The description of a " + commandType + " cannot be empty.");
    }
}
