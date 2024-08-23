public class EmptyDescriptionException extends YapperException {
    public EmptyDescriptionException(String commandType) {
        super("Eh yo The description of a " + commandType + " cannot be empty.");
    }
}
