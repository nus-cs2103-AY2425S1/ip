public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("     Like a dumpling, tasks cannot be empty! Please provide a descriptive name.");
    }
}
